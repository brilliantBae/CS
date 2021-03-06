# Garbage Collector

가비지 컬렉션에 대해 간단하게 정리해보려고 한다.

자바에서는 메모리 관리를 JVM이 담당하고 있는데 이를 위해 Garbage Collector 라는 녀석을 두어서 힙 영역 내의 쓸모없어진(unreachable)객체들을 소멸시키고 메모리를 회수한다.

이런 GC 의 역할을 담당하는 전용 스레드가 JVM 내부에 존재하는데 이 스레드는 일반적인 스레드와 조금 다르다.

사실 GC 는 일반적인 작업보다 우선순위가 높을 특별한 이유가 없다. 그렇기 때문에 JVM은 일반 작업을 수행하는 스레드와 다른 스레드에 GC 를 담당하게 하는데, 이를 Daemon thread 라고 한다.

Daemon thread 는 백그라운드에서 활동하는 실행 우선순위가 낮은 스레드를 말한다. 

그러나, 우선순위가 낮아도 어떤 한순간은 이 데몬 스레드가 CPU 를 점유해야 하는 순간이 올 것이다. 이 순간을 'stop-the-world' 라고 하는데 GC을 실행하기 위해 JVM이 애플리케이션 실행을 멈추기 때문에 이렇게 부른다.

당연하게 stop-the-world가 발생하면 GC를 실행하는 쓰레드를 제외한 나머지 쓰레드는 모두 작업을 멈춘다. GC 작업을 완료한 이후에야 중단했던 작업을 다시 시작한다. 어떤 GC 알고리즘을 사용하더라도 stop-the-world는 발생한다. 

그렇기 때문에 관건은 이 stop-the-world 시간을 줄이는 것이다.