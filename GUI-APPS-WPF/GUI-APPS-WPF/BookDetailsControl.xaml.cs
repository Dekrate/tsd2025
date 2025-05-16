using System.Windows;
using System.Windows.Controls;
using System.Diagnostics; 

namespace GUI_APPS_WPF
{
    public partial class BookDetailsControl : UserControl
    {
        public static readonly RoutedEvent DeleteRequestedEvent = EventManager.RegisterRoutedEvent(
            "DeleteRequested", RoutingStrategy.Bubble, typeof(RoutedEventHandler), typeof(BookDetailsControl));

        public event RoutedEventHandler DeleteRequested
        {
            add => AddHandler(DeleteRequestedEvent, value);
            remove => RemoveHandler(DeleteRequestedEvent, value);
        }

        public static readonly DependencyProperty BookDetailProperty =
            DependencyProperty.Register(
                "BookDetail",
                typeof(Book),
                typeof(BookDetailsControl),
                new FrameworkPropertyMetadata(
                    null,
                    FrameworkPropertyMetadataOptions.BindsTwoWayByDefault,
                    OnBookDetailChanged 
                )
            );

        public Book BookDetail
        {
            get { return (Book)GetValue(BookDetailProperty); }
            set
            {
                Debug.WriteLine($"BookDetail setter called. NewValue is: {value?.Title ?? "null"}");
                SetValue(BookDetailProperty, value);
            }
        }

        public static readonly DependencyProperty IsBookReadProperty =
            DependencyProperty.Register(
                "IsBookRead",
                typeof(bool),
                typeof(BookDetailsControl),
                new PropertyMetadata(false, OnIsBookReadChanged));

        public bool IsBookRead
        {
            get { return (bool)GetValue(IsBookReadProperty); }
            set { SetValue(IsBookReadProperty, value); }
        }

        private static void OnIsBookReadChanged(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            Debug.WriteLine($"IsBookRead changed to: {e.NewValue}");
        }


        private static void OnBookDetailChanged(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            BookDetailsControl control = (BookDetailsControl)d;
            Debug.WriteLine($"OnBookDetailChanged called. NewValue is: {(e.NewValue as Book)?.Title ?? "null"}");

            control.DeleteButton.IsEnabled = (e.NewValue is Book);

            control.IsBookRead = (e.NewValue as Book)?.IsRead ?? false;
        }

        public BookDetailsControl()
        {
            InitializeComponent();
        }

        private void DeleteButton_Click(object sender, RoutedEventArgs e)
        {
            Book bookToDelete = this.BookDetail; 

            if (bookToDelete != null)
            {
                MessageBoxResult result = MessageBox.Show($"Are you sure you want to delete the book: \"{bookToDelete.Title}\"?", "Confirm Deletion", MessageBoxButton.YesNo, MessageBoxImage.Question);

                if (result == MessageBoxResult.Yes)
                {
                    RaiseEvent(new RoutedEventArgs(DeleteRequestedEvent, this));
                }
            }
        }
    }
}
