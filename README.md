# DI-Example
This is an example implementation of Dependency Injection in Java. This is a sample process of Multiple payment services in a ECommerce Web site. Dependency Injection is a commonly used design pattern. 

As per the wikipedia,

In software engineering, dependency injection is a technique in which an object receives other objects that it depends on. These other objects are called dependencies. In the typical "using" relationship[1] the receiving object is called a client and the passed (that is, "injected") object is called a service. The code that passes the service to the client can be many kinds of things and is called the injector. Instead of the client specifying which service it will use, the injector tells the client what service to use. The "injection" refers to the passing of a dependency (a service) into the object (a client) that would use it.


## Design considerations
There are at least three ways a client object can receive a reference to an external module:[37]

constructor injection
  The dependencies are provided through a client's class constructor.
setter injection
  The client exposes a setter method that the injector uses to inject the dependency.
interface injection
  The dependency's interface provides an injector method that will inject the dependency into any client passed to it. Clients must      implement an interface that exposes a setter method that accepts the dependency.

What I have considered is the Constructor Injection.  Please refer to the ECommerceSite class. PaymentService is loosely coupled and It is injected via PaymentServiceInjector
Similar to Spring framework, @Scope annotation I have created a custom annotation called ServiceScope. PaymentServiceInjector will consider the ServiceScope annotation and will created instances of PaymentService.

## Limitations
This is an bare implementation. I have only completed the SINGLETON and PROTOTYPE annotations. 

I couldnt create a common method in PaymentServiceInjector to create PaymentService instances based on the class. Right now There are two identical code sections in there. Only change is the getting "Class<?> paymentServiceClass" value 

I have created simple units test cases to check whether the functionality works okay. to avoid future database connections, I have mocked the processPayment(PaymentRequest paymentRequest) method. If I havent mocked this, I can test the bean scope behaviour in unit test cases.


## Getting Started / Usage Guide

This is a maven based project. It will require java 1.8
Main Class in this project is MyWebSiteTest. When you run the main method, you can see two payment services (CashPaymentServiceImpl,CCPaymentServiceImpl) behaving in Singleton bean scope and Prototype bean scope. 

This can be tested via checking the processedSources list size. changing the below annotation value, you can test the SINGLETON and PROTOTYPE behavior.
@ServiceScope(ScopeType.PROTOTYPE)
