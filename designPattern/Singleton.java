public class Singleton {
    private static Singleton singletonObject;

    private Singleton() {
    }
    // 메소드를 동기화시켜 인스턴스 생성이 여러번 일어나지 않도록 한다.
    public static synchronized Singleton getSingletonObject(){
        if(singletonObject == null){
            singletonObject = new Singleton();
        }
        return singletonObject;
    }
}
