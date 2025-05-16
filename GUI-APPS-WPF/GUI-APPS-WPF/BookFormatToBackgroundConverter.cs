using System.Globalization;
using System.Windows.Data;
using System.Windows.Media;
using System.Windows;

namespace GUI_APPS_WPF
{
    public class BookFormatToBackgroundConverter : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
            if (value is BookFormat format)
            {
                if (format == BookFormat.PaperBack)
                {
                    return new SolidColorBrush(Colors.LightGray);
                }
            }
            return Brushes.Transparent;
        }

        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            return DependencyProperty.UnsetValue;
        }
    }
}