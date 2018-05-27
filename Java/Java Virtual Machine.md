# Java Virtual Machine (JVM)

JVM은 자바 가상 머신이다. 이에 대해 설명하기 이전에 우리가 프로그래밍 언어로 코드를 짜고 그 프로그램을 실행시키는 과정을 살펴보자.

C언어의 컴파일 과정과 실행 과정을 Java와 비교해보려고 한다.

먼저, 일반적으로 프로그램이 실행되는 과정을 간단하게 설명해보자.

**Compile**

우리가 프로그래밍 언어를 사용해 소스코드를 작성하고 ide에서 실행 버튼을 누르면, 컴파일러가 소스코드를  기계어로 이루어진 `.obj` 파일로 변환한다.

**Linking**

그 다음 Linker가 여러개의 오브젝트 파일을 하나의 오브젝트 파일로 묶는다.

이제 실행 가능한 파일, `.exe` 가 Linker를 통해 생성이 되었고 사용자의 프로그램 실행 요청이 있으면 이 파일은 OS의 로더에 의해 메모리에 깔려 실행되게 된다.

![052016_0614_workingofja5](https://user-images.githubusercontent.com/23162178/40583800-762ae51c-61d0-11e8-8015-8b39f88b7125.jpg)



![052016_0614_workingofja6](https://user-images.githubusercontent.com/23162178/40583944-7bf56550-61d3-11e8-9c90-1b83bfb3a660.jpg)

**(References : https://www.guru99.com/java-virtual-machine-jvm.html)**



그런데 Java의 경우, 이 과정이 C언어 하고는 조금 다르다.

Java의 경우에는 컴파일러를 통해 완전한 기계어의 형태가 아니라 그 중간형태의 언어인 ByteCode 로 변환이 되고, Linking 과정 없이 메모리에 올라가게 된다.

이게 가능한 이유는 JVM이 존재하기 때문이다. 

![052016_0614_workingofja7](https://user-images.githubusercontent.com/23162178/40584006-9abf85aa-61d4-11e8-86c8-4689bc4ecb5d.jpg)

**(References : https://www.guru99.com/java-virtual-machine-jvm.html)**

자바 프로그램이 실행이 되면, 메모리에 JVM이 올라가 ByteCode 형태의 .class 파일을 해석하고 실행하는 것이다. 바로 아래 그림처럼 말이다.

![052016_0614_workingofja8](https://user-images.githubusercontent.com/23162178/40584040-33797f9e-61d5-11e8-85d6-7bbb438cbf8f.jpg)



**(References : https://www.guru99.com/java-virtual-machine-jvm.html)**

이렇기 때문에 모든 자바 프로그램들은 JVM위에서 실행된다고 보면 된다. 

이제 구체적으로 JVM에 대해 알아보자.



### JVM이란?

* 자바 애플리케이션을 클래스 로더를 통해 읽어 들여 자바 API와 함께 실행한다.

* 자바 애플리케이션들이 JVM 위에서 실행되기 때문에 OS 종류에 따른 JVM만 설치해준다면 자바 소스코드를 한줄도 수정하지 않아도 OS 종류에 관계없이 자바 프로그램을 실행시킬 수 있다.

* 메모리 관리, Garbage collection 을 수행한다.

* 스택 기반으로 동작한다.

* 사실은 JVM 역시 프로그램이기 때문에 JVM 이 실행되는 순간 프로세스가 생성되는 것이다.

  ​

JVM을 통해 자바 프로그램이 실행되는 과정은 다음과 같다.

> 1. 프로그램이 실행되면 시스템 콜을 통해 자바 프로그램이 필요로 한 만큼의 메모리를 할당받는다. JVM은 이 메모리를 용도에 따라 여러 영역으로 나누어 관리한다.
> 2. 자바 컴파일러(java)가 자바 소스코드 파일(.java)을 컴파일해 바이트 코드형태인 .class 파일로 변환 시킨다.
> 3. JVM의 Class Loader 가 .class 파일들을 JVM 내부로 로딩한다.
> 4. 로딩된 class파일들은 Execution engine을 통해 해석된다.
> 5. 해석된 바이트코드는 Runtime Data Areas 에 배치되어 실질적인 수행이 이루어지게 된다.
> 6. 이러한 실행과정 속에서 JVM은 필요에 따라 Thread Synchronization과 GC같은 관리작업을 수행한다.

**(References :http://asfirstalways.tistory.com/158)**



![jvm](https://user-images.githubusercontent.com/23162178/40587259-ac1e4f74-6207-11e8-8eb3-35fa770a4ce1.jpg)

**(References :http://asfirstalways.tistory.com/158)**



Class Loader 와 Runtime Data Area 등 각각의 구성요소에 대한 자세한 설명은 [여기](http://asfirstalways.tistory.com/158)를 참고하길 바란다. 



### References

* https://www.guru99.com/java-virtual-machine-jvm.html
* http://asfirstalways.tistory.com/158

