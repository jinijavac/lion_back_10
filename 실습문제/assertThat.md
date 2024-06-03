AssertJ는 Java 애플리케이션에서 테스트를 더 쉽게 작성할 수 있게 도와주는 강력한 테스트 라이브러리입니다. 


### 기본 사용법

AssertJ의 `assertThat`을 사용하여 다양한 타입의 값을 검증할 수 있습니다. 기본적인 사용법은 다음과 같습니다:

```java
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AssertJExampleTest {

    @Test
    public void testString() {
        String actual = "hello world";
        assertThat(actual).isEqualTo("hello world")
                          .startsWith("hello")
                          .endsWith("world")
                          .contains("lo wo");
    }

    @Test
    public void testNumber() {
        int actual = 10;
        assertThat(actual).isEqualTo(10)
                          .isGreaterThan(5)
                          .isLessThan(20);
    }

    @Test
    public void testCollection() {
        List<String> actual = Arrays.asList("apple", "banana", "cherry");
        assertThat(actual).hasSize(3)
                          .contains("banana")
                          .doesNotContain("grape")
                          .containsExactly("apple", "banana", "cherry");
    }

    @Test
    public void testObject() {
        Object actual = new Object();
        assertThat(actual).isNotNull();
    }
}
```

### 주요 기능

AssertJ는 다양한 타입에 대한 어설션을 지원합니다. 몇 가지 주요 기능을 소개하겠습니다:

#### 문자열 어설션
```java
@Test
public void testString() {
    String actual = "hello world";
    assertThat(actual).isEqualTo("hello world")
                      .startsWith("hello")
                      .endsWith("world")
                      .contains("lo wo")
                      .isNotEmpty()
                      .hasSize(11);
}
```

#### 숫자 어설션
```java
@Test
public void testNumber() {
    int actual = 10;
    assertThat(actual).isEqualTo(10)
                      .isGreaterThan(5)
                      .isLessThan(20)
                      .isBetween(1, 15);
}
```

#### 컬렉션 어설션
```java
@Test
public void testCollection() {
    List<String> actual = Arrays.asList("apple", "banana", "cherry");
    assertThat(actual).hasSize(3)
                      .contains("banana")
                      .doesNotContain("grape")
                      .containsExactly("apple", "banana", "cherry")
                      .doesNotHaveDuplicates();
}
```

#### 객체 어설션
```java
@Test
public void testObject() {
    Object actual = new Object();
    assertThat(actual).isNotNull();
}
```

### 커스텀 어설션

AssertJ는 커스텀 어설션을 작성하는 기능도 제공합니다. 이를 통해 도메인 객체에 대한 보다 직관적이고 읽기 쉬운 어설션을 작성할 수 있습니다.

#### 예제: 사용자 클래스에 대한 커스텀 어설션
```java
public class User {
    private String name;
    private int age;

    // getters and setters
}

public class UserAssert extends AbstractAssert<UserAssert, User> {
    public UserAssert(User actual) {
        super(actual, UserAssert.class);
    }

    public static UserAssert assertThat(User actual) {
        return new UserAssert(actual);
    }

    public UserAssert hasName(String name) {
        isNotNull();
        if (!actual.getName().equals(name)) {
            failWithMessage("Expected user's name to be <%s> but was <%s>", name, actual.getName());
        }
        return this;
    }

    public UserAssert hasAge(int age) {
        isNotNull();
        if (actual.getAge() != age) {
            failWithMessage("Expected user's age to be <%s> but was <%s>", age, actual.getAge());
        }
        return this;
    }
}

public class CustomAssertionTest {

    @Test
    public void testUser() {
        User user = new User();
        user.setName("John");
        user.setAge(25);

        UserAssert.assertThat(user).hasName("John")
                                   .hasAge(25);
    }
}
```

