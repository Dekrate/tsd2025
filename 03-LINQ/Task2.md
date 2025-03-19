# Task 2

## Task 2.1
```csharp
Func<int, bool> isLeapYear = year => (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
```

## Task 2.2
```csharp
using System;
using System.Collections.Generic;

public class RandomizedList<T>
{
    private List<T> _items;
    private Random _random;

    public RandomizedList()
    {
        _items = new List<T>();
        _random = new Random();
    }

    public void Add(T element)
    {
        if (_random.Next(2) == 0)
        {
            _items.Insert(0, element);
        }
        else
        {
            _items.Add(element);
        }
    }

    public T Get(int index)
    {
        int randomIndex = _random.Next(index + 1);
        return _items[randomIndex];
    }

    public bool IsEmpty()
    {
        return _items.Count == 0;
    }
}
```
