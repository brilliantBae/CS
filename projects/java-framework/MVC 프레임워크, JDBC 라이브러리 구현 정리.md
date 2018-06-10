# MVC 프레임워크, JDBC 라이브러리 구현 정리

## MVC 패턴 기반 프레임워크 구현

#### MVC 기반으로 개발해야 하는 이유? MVC 패턴의 장점

MVC 패턴은 사용자 요청을 처음으로 받는 컨트롤러, 컨트롤러한테서 사용자로부터 온 데이터를 전달받아 로직을 처리하는 모델 그리고 모델이 생성한 결과를 이용해 HTML 페이지를 생성하는 뷰 이렇게 역할이 나누어져 있다.

* Model 의 재사용

  이렇게 역할을 나눔으로써 특히 뷰와 모델의 역할을 분리했기 때문에 정보를 담고 있는 모델을 여러 뷰에서 재사용할 수 있다는 장점이 있다.

* 객체지향 프로그래밍에서의 SRP(Single Responsibility Principle) 준수

#### MVC 패턴의 특징

* 컨트롤러는 사용자 입력에 대한 유효성 체크를 담당한다.
* 모델은 실질적인 비즈니스 로직을 구현하는 역할을 담당하고 로직처리 결과를 DB에 저장하고 조회하는 역할
* Front Controller 인 `DispatcherServlet` 은 **모든 요청에 대한** 공통적인 처리, 예외 발생 시 일관된 처리를 담당한다.



#### 브라우저에서 요청이 왔을 때  MVC 패턴 기반의 요청 처리 과정

* 사용자가 url 을 입력하면 브라우저는 이를 바탕으로 HTTP Request Message 를 작성한다.

* 브라우저는 OS 에 의뢰해 웹서버에게 요청 메시지를 전달한다.

* 서블릿 컨테이너인 tomcat이 웹서버로부터 요청을 건네받고, server socket 을 생성하여 웹서버와 서블릿이 통신할 수 있도록 한다.

* 또한 `HttpServletRequest`, `HttpServletResponse` 객체를 생성한다.

* tomcat은  `HttpServletRequest` 에 사용자 요청을 담아 서블릿에게 전달해주고, 서블릿은 해당하는 사용자 요청들을 받게 된다.

  * 스프링에서는 `DispatcherServlet` 이 서블릿 역할을 하고 있고, 이것이 서블릿 컨테이너와 스프링 프레임워크를 연결해주는 부분이 된다는 것이 내 생각이다.

* `DispatcherServlet` 은 모든 사용자 요청을 맨 앞에서 처음으로 받게 되는데 이것을 Front Controller 구조라고 한다.  `DispatcherServlet`  내에서는 handler mapping 전략을 이용해 url 과 컨트롤러를 매핑시켜 각각의 컨트롤러들에게 요청을 위임한다. (이 작업은 한번만 실행되는 서블릿 초기화 메소드 `init()` 메소드 내에서 이루어지는 작업이다.)

* 서블릿으로부터 요청을 위임받은 컨트롤러는 모델 객체를 생성하고 넘겨받은 요청에서 데이터를 추출해 모델에 저장한다. (데이터에 대한 로직 처리는 service 계층 클래스에 위임한다.)

* 다음 컨트롤러는 **데이터를 저장한 모델 객체와 해당 모델을 나타낼 뷰에 대한 논리적인 정보**를 반환해준다. 이것으로 컨트롤러의 역할은 끝이 나는것이다. 이제부터는 주도권이 다시 `DispatcherServlet` 으로 넘어가게 된다.

* 뷰의 정보와 모델 객체를 넘겨받은  `DispatcherServlet` 은 실질적으로 뷰 객체를 생성하는데 이때 내부적으로 `ViewResolver` 라는 것을 내부적으로 이용해 뷰의 정보를 이용해 실질적인 뷰 객체를 찾아준다. 생성한 뷰 객체에 모델 객체를 전달하여 처리하도록 하고 최종결과물을 받아 그것을 `HttpServletResponse` 객체에 담아 다시 클라이언트에게 응답으로 보내준다.




## JDBC 라이브러리 구현

Spring 의 JdbcTemplate는 JPA 와 존재하는 목적이 비슷하다. 

JDBC api를 그냥 사용하게 되면, 자바 애플리케이션이 지향하는 객체지향 패러다임과 데이터베이스가 지향하는 데이터 중심 패러다임 사이에 존재하는 차이를 극복하기 위해 많은 양의 보일러플레이트 코드들이 생겨나기 때문에 이를 줄이고자 등장한 것이다.

둘의 차이가 있다면 JdbcTemplate은 sql 문을 개발자가 직접 작성해야 하므로 sql 에 의존하는 개발을 피할 수 없다는 점이다. 

JdbcTemplate은 SQL 매퍼라고 부르고 내부적으로 JDBC api를 사용하며 이름 그대로 객체와 SQL을 매핑한다. 

스프링에서는 JdbcTemplate과 비슷한 역할을 하는 애들을 여럿 제공하고 있다.

- JdbcTemplate
- NamedParameterJdbcTemplate
- SimpleJdbcTemplate
- SimpleJdbcInsert and SimpleJdbcCall

By using the JdbcTemplate class, you don't need to create connection,statement,start transaction,commit transaction and close connection to execute different queries. You can execute the query directly.

