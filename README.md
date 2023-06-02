## Test Double
- 테스트가 어려운 객체를 테스트할 수 있게 해주는 객체 (ex. 데이터베이스에 영향을 주고 받는 경우.)
- 스턴트맨(정식명칙: 스턴트더블)에서 비롯된 이름.
- 크게 Dummy,Fake,Stub,Spy,Mock이 있다.
    - 그러나 현재 우리의 상황에서 사용할만한 것은 Fake, Stub, Mock 세가지이므로 세가지만 이야기할 것.
    - Dummy: 특정 객체 생성에 필요한 객체이지만, 테스트에 사용될 일이 없어 테스트에 영향을 미치지 않는 객체.
    - Spy: Stub 호출 + 기록 기능

## **stubbing vs mocking**

### Stubbing

- stub: 테스트를 위해 의도한 결과만 반환되도록 하기 위한 객체
- stubbing: 테스트할 메서드의 기능을 설정하는 것

### **mocking**

- mock: 호출이 예상되는 메서드에 대해 기대한 동작과 값이 설정된 객체
- mocking: 설정된 메서드를 통해 기대값을 검증하는 것

## **mock object vs fake object**

### fake object

- 기능을 간략하게 재구현한 것
- 실제 객체와 동일한 역할을 하도록 만들어 사용하는 객체
  ⇒ 상태를 테스트할 때 용이

### mock object

- 기능의 결과를 가정한 것
- 상호작용을 테스트하는 것
  ⇒ 기능의 수행 여부, 동작을 검증할 때 용이

---

**불필요한 stubbing을 감지하는 방법**

- **Unnecessary stubbing**
    - https://mockk.io/#unnecessary-stubbing
    - 불필요한 스텁이 없는지 확인할 수 있습니다.
    - mock코드에 테스트 코드에서 사용하지 않는 일부 선언된 호출이 있는 경우 예외 발생. 불필요한 스텁을 선언했거나, 테스트된 코드가 예상되는 스텁을 호출하지 않는 경우 발생.
- **Automatic unnecessary stubbing check**
    - https://mockk.io/#automatic-unnecessary-stubbing-check
    - 테스트 클래스에 `@MockKExtension.CheckUnnecessaryStub`어노테이션을 추가해 모든 스텁 메서드가 한 번 이상 사용되었는지 확인할 수 있다.
    - 각 테스트가 끝난 후 모든 mock테스트에서 내부적으로 `checkUnnecessaryStub`을 호출하여 불필요한 스터빙이 없는지 확인한다.

---

- **relaxed**
    - https://mockk.io/#relaxed-mock
    - relaxed mock은 모든 함수에 대해 간단한 값을 반환한다. 각 경우에 대한 동작 지정을 생략 가능하다. 필요한 것은 따로 stubbing하면 된다. 참조 유형의 경우 연쇄 mock이 반환됩니다.
      사용중인 기능들 자동으로 stubbing되기 때문에 사용중인 기능들을 한눈에 확인할 수 없어짐.
      ⇒ 테스트를 놓치기 쉬워짐, 사용하지 않는 것이 좋음
      사용하고 싶다면, 테스트가 귀찮을 뿐이다
    - generic return types에서는 relaxed mocking이 제대로 작동하지 않는다. 이 경우 일반적으로 클래스 형 변환 예외가 발생. generic return types의 경우 수동으로 stubbing을 선택해야 한다.

---

## Test를 더 용이하게, 변경이 적도록, 작성할 수 있는 꿀팁

### Product Code를 작성할 때

- 의존성은 외부에서 주입
    1. 생성자
    2. 메서드
    - 내부에서 의존성을 만드는 것은 지양하기
        - mock의 목적(테스트의 목적에 맞춰 테스트 설계)에 유리함
          ex. Repo를 Presenter에서 생성한다면 테스트에서 Repo를 대체할 수 없음
          Object를 내부에서 직접 사용하는 것도 마찬가지다.

### Test Code를 작성할 때

- mock은 최소한으로
    - mock은 언제든 좋은 것이 아니다.
    - mock을 사용하는 것은 직접 호출하는 것이 아니라 호출한다고 가정하는 것 뿐이다.
      때문에 실제 프로프램이나 다른 테스트에 영향을 미칠 경우에만 최소한으로 사용하도록 하자.
- 각각의 테스트가 다른 테스트에 영향을 미치치 않도록 한다.
    - 테스트에 변경이 있을 경우 상호관계를 확인하기 위한 절차가 생긴다. (⇒ 테스트에 시간을 쓰게 되고 테스트와 사이가 안좋아지게 된다)
- 유연하게
    - 상태 검증도 좋지만, 이번에 연습한 것처럼 기능 수행 여부만으로도 충분하다 생각……..
- 제어 가능하게(현재시간, 랜덤 사용 등 사용하지 않기)

---

### 참고

- “Test Double”-테코블: https://tecoble.techcourse.co.kr/post/2020-09-19-what-is-test-double/
    - 테스트 더블 설명 굿
- 테스트를 작성하는 방법: ****https://blog.kingbbode.com/
    - 정말 제목 그대로 테스트를 작성하는 방법에 대해 잘 설명되어 있습니다.
- [10분 테코톡] 더즈, 티키의 Classic TDD VS Mockist TDD: https://youtu.be/n01foM9tsRo
- 안드로이드 CI&Lint&Unit Test (feat. 라인 안드로이드 개발자): https://youtu.be/gHC4NHN-ZdA
- [드로이드나이츠 2021] 강사룡 - Android Testing Best Practices: https://youtu.be/D_tWlb2deX8
- https://youtu.be/TjXuL_eSCPA