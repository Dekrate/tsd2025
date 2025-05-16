using System.ComponentModel;
using System.Runtime.CompilerServices;

namespace GUI_APPS_WPF
{
    public enum BookFormat
    {
        PaperBack,
        Hardcover,
        EBook,
        Audiobook
    }

    public class Book : INotifyPropertyChanged
    {
        private static int _nextId = 1; 

        private int _id;
        private string _title;
        private string _author;
        private bool _isRead;
        private int _year;
        private BookFormat _format;

        public int Id
        {
            get => _id;
            private set 
            {
                if (_id != value)
                {
                    _id = value;
                    OnPropertyChanged();
                }
            }
        }

        public string Title
        {
            get => _title;
            set
            {
                if (_title != value)
                {
                    _title = value;
                    OnPropertyChanged();
                }
            }
        }

        public string Author
        {
            get => _author;
            set
            {
                if (_author != value)
                {
                    _author = value;
                    OnPropertyChanged();
                }
            }
        }

        public bool IsRead
        {
            get => _isRead;
            set
            {
                if (_isRead != value)
                {
                    _isRead = value;
                    OnPropertyChanged();
                }
            }
        }

        public int Year
        {
            get => _year;
            set
            {
                if (_year != value)
                {
                    _year = value;
                    OnPropertyChanged();
                }
            }
        }

        public BookFormat Format
        {
            get => _format;
            set
            {
                if (_format != value)
                {
                    _format = value;
                    OnPropertyChanged();
                }
            }
        }

        public Book(int id)
        {
            Id = id;
        }

        public event PropertyChangedEventHandler? PropertyChanged;

        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }

        public static int GetNextId()
        {
            return _nextId++;
        }
    }

    public static class MyBookCollection
    {
        private static List<Book> _books;

        static MyBookCollection()
        {
            Book.GetNextId();

            _books = new List<Book>()
            {
                new Book(Book.GetNextId()){ Author = "J.K. Rowling", Format = BookFormat.EBook, IsRead = true, Title = "Harry Potter and the Philosopher's Stone", Year=1997},
                new Book(Book.GetNextId())
                {
                    Author = "J.K. Rowling", Format = BookFormat.EBook, IsRead = true, Title = "Harry Potter and the Chamber of Secrets",
                    Year = 1998
                },
                new Book(Book.GetNextId()){ Author = "J.K. Rowling", Format = BookFormat.PaperBack, IsRead = true, Title = "Harry Potter and the Prisoner of Azkaban", Year = 1999},
                new Book(Book.GetNextId()){ Author = "Jonathan Swift", Format = BookFormat.PaperBack, IsRead = false, Title = "Travels into Several Remote Nations of the World. In Four Parts. By Lemuel Gulliver, First a Surgeon, and then a Captain of several Ships", Year=1972},
                new Book(Book.GetNextId()){Author = "Wayne Thomas Batson", Format = BookFormat.EBook, IsRead = true, Title = "Isle of Swords", Year = 2007},
                new Book(Book.GetNextId()){Author = "Louis A. Meyer", Format = BookFormat.Audiobook, IsRead = false, Title = "Under the Jolly Roger", Year = 2005}
            };
        }

        public static List<Book> GetMyCollection()
        {
            return new List<Book>(_books);
        }

    }
}
