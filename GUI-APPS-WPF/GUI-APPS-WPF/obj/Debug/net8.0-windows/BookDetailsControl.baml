   M S B A M L   `   `   `  ����G  CGUI-APPS-WPF, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null+clr-namespace:GUI_APPS_WPFGUI_APPS_WPF  5      O Kmscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e0891&clr-namespace:System;assembly=mscorlibSystem 5      %    GUI_APPS_WPF.BookDetailsControl   5      W SPresentationCore, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=8.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5      R NSystem.Xaml, Version=8.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      ,localclr-namespace:GUI_APPS_WPF     5      9System&clr-namespace:System;assembly=mscorlib     5        � 	Resources  5         BookFormats S� 
MethodName S� 
ObjectTypeU QSystem.Runtime, Version=8.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a  System.Enum S� MethodParameters M� TypeName  IdTextBlockStyle �� 
TargetType �� Triggers �� Binding �� RelativeSource	 �� AncestorType%   GUI_APPS_WPF.BookDetailsControl
 �� Value �� Property �� Value��%1  &	        &	 m     S� 5   
   $ 	GetValues��61   "  6H    5      M� 5      $ local:BookFormat��6   5      �� 5   
   " ��6)    5      �� 5       6   �� *
IsBookRead+ �� "	  
 True6�   .a�5      ��  � ��$ Strikethrough{�68   5      5      5      .��5      � $��10q�6    � RowDefinitions 5   
   �� 5      $
��Auto �6   �� 5      $
��Auto �6   �� 5      $
��Auto �6   �� 5       $
��Auto �6   �� 5!      $
��Auto �6   �� 5"      $
��Auto �6   �� 5#      $��* �6   �� 5$      $
��Auto �6    � ColumnDefinitions 5&   
   �� 5'      $
��Auto �6   �� 5(      $��* �6   .+�5+   
   �� $��0��6   $��0��6!   $	��ID:��61   $
��Bold"�6<   $��0,0,10,5q�6N   �� 5,   
   $��0��6   $��1��6!   $��0,0,0,5q�61   #��[ 5-      ��5.      ��  �� "	   �� Path$ BookDetail.Id��� 50   
   $��1��6   $��0��6!   $��Title:��61   $
��Bold"�6?   $��0,0,10,5q�6Q   �� 51   
   $��1��6   $��1��6   $��0,0,0,5q�6/   ��52      ��  �� "	  $ BookDetail.Title� �� UpdateSourceTrigger$ PropertyChanged=��� 54   
   $��2��6   $��0��6!   $��Author:��61   $
��Bold"�6@   $��0,0,10,5q�6R   �� 55   
   $��2��6   $��1��6   $��0,0,0,5q�6/   ��56      ��  �� "	  $ BookDetail.Author�$ PropertyChanged=��� 58   
   $��3��6   $��0��6!   $��Year:��61   $
��Bold"�6>   $��0,0,10,5q�6P   �� 59   
   $��3��6   $��1��6   $��0,0,0,5q�6/   ��5:      ��  �� "	  $ BookDetail.Year�$ PropertyChanged=��� 5<   
   $��4��6   $��0��6!   $��IsRead:��61   $
��Bold"�6@   $��0,0,10,5q�6R   �� 5=   
   $��4��6   $��1��6    $��0,5,0,5q�60   $��Center=�6A    d� 	IsChecked 5>      ��  �� "	  $ BookDetail.IsRead��� 5@   
   $��5��6   $��0��6!   $��Format:��61   $
��Bold"�6@   $��0,0,10,5q�6R   �� 5A   
   $��5��6   $��1��6    $��0,0,0,5q�60   ��5B      ��  �� Source# [   �� SelectedItem 5C      ��  �� "	  $ BookDetail.Format��� 5E   
   $��7��6   $��0��6"   $��2��62    �� Orientation$ 
Horizontal=�6F   $��Right=�6_   .+�5F      �� -   5         ��Name$ DeleteButton��5F      $��Delete��6+   $��5q�6W   5G      5H      5I      