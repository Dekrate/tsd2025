using System;
using System.Collections.ObjectModel;
using System.ComponentModel; 
using System.Runtime.CompilerServices;
using System.Windows;
using System.Diagnostics; 
using System.Windows.Media;
using System.Windows.Input;

namespace GUI_APPS_WPF
{
    public partial class MainWindow : Window, INotifyPropertyChanged
    {
        private ObservableCollection<Book> _books;
        public ObservableCollection<Book> Books
        {
            get { return _books; }
            set
            {
                if (_books != value)
                {
                    _books = value;
                    OnPropertyChanged();
                }
            }
        }

        private Book _selectedBook;
        public Book SelectedBook
        {
            get { return _selectedBook; }
            set
            {
                if (_selectedBook != value)
                {
                    _selectedBook = value;
                    OnPropertyChanged();

                    Debug.WriteLine($"SelectedBook changed to: {value?.Title ?? "null"}");

                    if (DetailsControl != null)
                    {
                        DetailsControl.BookDetail = value;
                    }
                }
            }
        }

        private double _greenValue = 240; 
        public double GreenValue
        {
            get { return _greenValue; }
            set
            {
                if (_greenValue != value)
                {
                    _greenValue = value;
                    OnPropertyChanged();
                    UpdateHeaderBackground();
                }
            }
        }

        private SolidColorBrush _headerBackground;
        public SolidColorBrush HeaderBackground
        {
            get { return _headerBackground; }
            set
            {
                if (_headerBackground != value)
                {
                    _headerBackground = value;
                    OnPropertyChanged();
                }
            }
        }


        public MainWindow()
        {
            InitializeComponent();

            Books = new ObservableCollection<Book>(MyBookCollection.GetMyCollection());

            DataContext = this;

            UpdateHeaderBackground();

        }

        private void UpdateHeaderBackground()
        {
            byte greenByte = (byte)Math.Round(GreenValue);
            HeaderBackground = new SolidColorBrush(Color.FromRgb(240, greenByte, 240));
        }


        public event PropertyChangedEventHandler? PropertyChanged;

        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }

        private void AddBookButton_Click(object sender, RoutedEventArgs e)
        {
            Book newBook = new Book(Book.GetNextId())
            {
                Title = "New Book Title",
                Author = "New Author",
                Year = DateTime.Now.Year,
                IsRead = false,
                Format = BookFormat.PaperBack 
            };

            Books.Add(newBook);

            SelectedBook = newBook;
        }

        private void DetailsControl_DeleteRequested(object sender, RoutedEventArgs e)
        {

            if (SelectedBook != null)
            {
                Books.Remove(SelectedBook);

                SelectedBook = null;
            }
        }

        private void DeleteBookCommand_CanExecute(object sender, CanExecuteRoutedEventArgs e)
        {
            e.CanExecute = SelectedBook != null;
        }

        private void DeleteBookCommand_Executed(object sender, System.Windows.Input.ExecutedRoutedEventArgs e)
        {
            DetailsControl_DeleteRequested(null, new RoutedEventArgs());
        }
    }
}
