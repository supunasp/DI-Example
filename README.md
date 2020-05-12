# DI-Example
This is an example implementation of Dependency Injection in Spring Framework. Dependency Injection is a commonly used design pattern. 

As per the wikipedia,

In software engineering, dependency injection is a technique in which an object receives other objects that it depends on. These other objects are called dependencies. In the typical "using" relationship[1] the receiving object is called a client and the passed (that is, "injected") object is called a service. The code that passes the service to the client can be many kinds of things and is called the injector. Instead of the client specifying which service it will use, the injector tells the client what service to use. The "injection" refers to the passing of a dependency (a service) into the object (a client) that would use it.


To demonstrate the functionality, I have created a sample process of confirm payment via payment services in a Web site. 

## Design considerations
There are at least three ways a client object can receive a reference to an external module

constructor injection
  The dependencies are provided through a client's class constructor.
setter injection
  The client exposes a setter method that the injector uses to inject the dependency.
interface injection
  The dependency's interface provides an injector method that will inject the dependency into any client passed to it. Clients must      implement an interface that exposes a setter method that accepts the dependency.


What I have considered is the Constructor Injection.  Please refer to the implementations of "WebSite" interface.
Similar to Spring framework, I have created two custom annotations.
    They are
      1. @ServiceScope annotation - similar to @Scope annotation in spring
      2. @Autowired annotation  - similar to @Autowired annotation in spring

I have created a separate class called "AutoWiredInjector" which will inject the dependencies to the instances passed to it, based on the @Autowired annotation in field and @ServiceScope annotation in FieldType class.



## Limitations
This is an bare implementation. I have only completed the SINGLETON and PROTOTYPE annotations. 

I have created a AutoWiredInjector which will initialize all the fields in classes and fields in parent class also.

All the time when a new instance is created, "public void injectBeans(Object object) throws Exception" should be called. This is a java program. not a web service. Therefore it is similar to the IOC container but it is not done in background. Developer has to call "public void injectBeans(Object object) throws Exception" every time a instance is created. please refer "MyWebSiteTest" class.

Currently AutoWiredInjector will inject dependencies. But if a field is an interface, then it wont be initialized. This has to be developed. Currently it will throw InstantiationException.

I have created simple units test cases to check whether the functionality works okay. to avoid future database connections, I have mocked the processPayment(PaymentRequest paymentRequest) method. If I havent mocked this, I can test the bean scope behaviour in unit test cases.


## Getting Started / Usage Guide

This is a maven based project. It will require java 1.8
Main Class in this project is MyWebSiteTest. When you run the main method, you can see three Website classes (CashPaymentSite, CCPaymentSite, HybridPaymentSite) which have fields of two payment services (CashPaymentServiceImpl, CCPaymentServiceImpl) behaving in Singleton bean scope and Prototype bean scope. Based on the @Autowired annotation, dependencies will be injected

This can be tested via checking the processedSources list size. changing the below annotation value, you can test the SINGLETON and PROTOTYPE behavior.
@ServiceScope(ScopeType.PROTOTYPE)
