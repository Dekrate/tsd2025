   M S B A M L   `   `   `  ����G  CGUI-APPS-WPF, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null+clr-namespace:GUI_APPS_WPFGUI_APPS_WPF  5   	       GUI_APPS_WPF.MainWindow   5   	   W SPresentationCore, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=8.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5   	   R NSystem.Xaml, Version=8.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   ,localclr-namespace:GUI_APPS_WPF     5   	     � Title$  Book Collection Manager��5   	   $	��600��6)   $	��800��66    � DataContext 5	   	   ��  �� RelativeSource �� *Self+ � 	Resources 5       �� MergedDictionaries �� Source2   ,GUI_APPS_WPF.BookFormatToBackgroundConverter #  BookFormatToBackgroundConverter  DeleteBookCommand�� 5   
    5      �� 5      $ BookStyles.xaml?�6%   %:   &	        &	        5      64   �� 5      6   5       J� CommandBindings 5      )  #System.Windows.Input.CommandBinding  5   
   -   5           Command# [ 5      5      .��5       �  � RowDefinitions 5!   
   �� 5"      $
��Auto �6   �� 5#      $��5* �6   �� 5$      $
��Auto �6   .+�5'   
   �� $��0��6   	 �� Padding$	 10q�6   
�������$��0,0,0,1q�6>   ��5(      �� *HeaderBackground+._�5)      � 
 � ColumnDefinitions
 5*      �� 5+      $��* �6'   �� 5,      $
��Auto �6'   �� 5-      $��* �6'   .+�5/      �� $��1��6   $5��/https://placehold.co/60x60/80ff80/000?text=Book��6(   $��Uniform=�6a   $��Center=�6s   �� 50      $��1��6   $��BOOK COLLECTION MANAGER��6,   $��Center=�6K   $��Center=�6h   $��20(�6�   $
��Bold"�6�   $��70,0,0,0q�6�   51      52      � 54   
   $��1��6   
 55      �� 56      $��3* �6#   �� 57      $��2* �6#   .+�5:      �� $��0��6   $��5q�6)   .+�5;      �� $��My Books��6   $
��Bold"�6,   $��0,0,0,5q�6>   `� 5=      .�5>      c� .@�5?      �� $��Delete Selected Book��6!   #��[ 6@   5@      5A      &   System.Windows.Controls.DataGrid  5C      -   5          Name$ BooksDataGrid��5C         AutoGenerateColumns$ False��5F         
IsReadOnly$ False��5G         SelectionMode$ Single=�5H         HeadersVisibility$ Column=�5I         GridLinesVisibility$ 
Horizontal=�5J         CanUserAddRows$ False��5K         CanUserDeleteRows$ False��5L        DataGridRowStyle   RowStyle# [ 5M      ��5D      �� *Books+ �� SelectedItem 5E      �� *SelectedBook+ �� Mode$ TwoWay=�   Columns 5N      0  *System.Windows.Controls.DataGridTextColumn  5O      ,  &System.Windows.Controls.DataGridColumn   Header$ ID��6-      
IsReadOnly$
 True��6P      Width5  /System.Windows.Controls.DataGridLengthConverter$
 Auto 6b   1  +System.Windows.Controls.DataGridBoundColumn   Binding 69   �� *Id+6b     5P      $ Title��6-   $ * 6V     UnreadBookCellStyle   	CellStyle# [ 5Q   -    5P   <   �� *Title+5Q   -   4  .System.Windows.Controls.DataGridCheckBoxColumn  5R      $ IsRead��61   $
 Auto 6\    6A   �� *IsRead+6\     5S      $ Author��6-   $ * 6X    6=   �� *Author+6X     5T      $
 Year��6-   $
 Auto 6T    6;   �� *Year+6T     5U      $ Format��6-   $
 Auto 6X    6=   �� *Format+6X   5W      �� 5X      -   5        $	��Add��5X      $��0,10,0,0q�6'   $
��Left=�69   $��5q�6T   6`   5Y      �� 5[      $��1��6   
�������$��1q�68   $��5q�6L   $	 10q�6W   ._�5\      %	   GUI_APPS_WPF.BookDetailsControl	  -   5         	 Name$ DetailsControl��5\   +   %
   GUI_APPS_WPF.BookDetailsControl 
  DeleteRequested$$ DetailsControl_DeleteRequested��5^   +    	  
BookDetail 5]   +   �� *SelectedBook+$ TwoWay=�5^   +   5_      5`      �� 5b   
   $��2��6   ���#f0f0f0$	 10q�63   
�������$��0,1,0,0q�6S   ._�5c      ��  �� Orientation$ 
Horizontal=�6   $��Center=�62   .+�5d      �� $��Adjust Green Color:��6   $��0,0,10,0q�67   �� 5e      $	��150��6     � Minimum$  0Q�6%   ! � Maximum$	! 255Q�61   " � Value" 6?   �� *
GreenValue+$ TwoWay=��� 5f      $��40��6   $��10,0,5,0q�6f   ��6%   �� *
GreenValue+# �� UpdateSourceTrigger$# PropertyChanged=�6f   5g      5h      5i      5j      