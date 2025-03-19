# Task 1
Implementation in Java
```java
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomizedList<T> {

    private List<T> items;
    private Random random;

    public RandomizedList() {
        items = new ArrayList<>();
        random = new Random();
    }

    public void add(T element) {
        if (random.nextInt(2) == 0) {
            items.add(0, element);
        } else {
            items.add(element);
        }
    }


    public T get(int index) {
        int randomIndex = random.nextInt(index + 1);
        return items.get(randomIndex);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
```

## Comparison (I think it's too much)

| **Feature**                | **Java**                                           | **.NET (C#)**                                    | **Python**                                          |
|----------------------------|----------------------------------------------------|-------------------------------------------------|----------------------------------------------------|
| **Generics Support**        | Fully integrated into the language.               | Fully integrated into the language.             | Optional via the `typing` module.                  |
| **Type Safety**             | Strong type checking at compile time.             | Strong type checking at compile time.           | Type checking is optional and done statically using tools like `mypy`. |
| **Syntax for Collections**  | `List<T>`, `Map<K, V>`, `Set<T>`                   | `List<T>`, `Dictionary<TKey, TValue>`, `IEnumerable<T>` | `List[T]`, `Dict[K, V]`, `Optional[T]` (using `typing`) |
| **Type Parameters**         | Specified as `T`, `K`, `V`, etc. in method or class definitions. | Specified as `T`, `K`, `V`, etc. in method or class definitions. | Specified as `T`, `K`, `V`, etc. in method or class definitions (via `typing`). |
| **Runtime Type Checking**   | Type parameters are erased at runtime (type erasure). | Type parameters are erased at runtime (type erasure). | No type checks at runtime (dynamic typing).        |
| **Generics in Methods**     | Fully supported, with flexible type bounds (`T extends SomeType`). | Fully supported with constraints (`T : SomeType`). | Type hints for methods are optional (using `typing` module). |
| **Extending/Constraining Generics** | Supports upper and lower bounds for generics (`T extends SomeClass`). | Supports constraints (`T : BaseClass`).         | Supports `TypeVar` with bounds using `typing` (e.g., `TypeVar('T', bound=SomeClass)`). |
| **Flexibility**             | Moderate flexibility with some constraints.       | Very flexible with constraints and covariance/contravariance. | Very flexible but not enforced, dynamic typing is a key feature. |
| **Code Readability**        | Strongly typed, leading to more readable code due to compile-time checks. | Strongly typed, which ensures safer and more readable code. | Type hints help, but less strict than Java and C#. |
| **Use in Collections**      | Often used with collections (e.g., `List<T>`, `Map<K, V>`). | Extensively used in collections (e.g., `List<T>`, `Dictionary<TKey, TValue>`). | Used optionally in collections via the `typing` module (e.g., `List[T]`, `Dict[K, V]`). |
| **Performance**             | Generic types are erased at runtime, but there's no performance penalty for the most part. | Similar to Java, performance is optimized with type erasure. | No compile-time type checking, but performance is flexible due to dynamic typing. |
| **Examples**                | `List<String>`, `Map<Integer, String>`             | `List<int>`, `Dictionary<string, string>`        | `List[int]`, `Dict[str, int]` (via `typing` module). |

# Task 2
For example we can use XQuery

```xquery
let $goldPrices := doc("goldPrices.xml")//Price
let $sortedDescending := $goldPrices order by $price/Value descending
let $sortedAscending := $goldPrices order by $price/Value ascending
return
    (
        <Top3HighestPrices>{for $price in $sortedDescending return <Price>{$price/Value}</Price>}</Top3HighestPrices>,
        <Top3LowestPrices>{for $price in $sortedAscending return <Price>{$price/Value}</Price>}</Top3LowestPrices>
    )
```
