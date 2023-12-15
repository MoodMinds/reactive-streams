# Enhancing Reactive Streams with Optional Subscription Support and Context Propagation

## Motivation

As per a user’s request, a [Reactive-Streams](https://github.com/reactive-streams/reactive-streams-jvm) extension was introduced
to enable `SubscribeSupport` (items **Producer**) implementations (which are equivalent to the `Publisher` in [Reactive-Streams](https://github.com/reactive-streams/reactive-streams-jvm))
to optionally support subscription and throw the `SubscribeSupportException` if subscriptions are not supported by their nature.

Another feature that is required is to enable a key-value pairs **Context** propagation bounded to a **Subscription**.

## Abstraction Overview

This **Reactive Streams** extension to the [Reactive-Streams](https://github.com/reactive-streams/reactive-streams-jvm) includes:

`SubscribeSupport`

With its method `subscribe` serving as a **Producer** of items and taking a `SubscribeSupport.Subscriber`
instance as an argument, this component is similar to the `Publisher` from the [Reactive-Streams](https://github.com/reactive-streams/reactive-streams-jvm),
except it allows throwing of a `SubscribeSupportException` if subscription is not supported by the implementation and
accepts additional bounded key-value pairs `Association` ([Elemental](https://github.com/MoodMinds/elemental)) context
which can be propagated to upstream item **Producers**.

`SubscribeSupport.Subscriber`

An items and other signals receiver from the `SubscribeSupport` (**Producer**) extending the one from the
[Reactive-Streams](https://github.com/reactive-streams/reactive-streams-jvm).

`SubscribeSupportException`

An exception indicating that asynchronous subscription inherently is not supported.

## Getting Started

Include **Reactive Streams** in your project by adding the dependency.

## Maven configuration

Artifacts can be found on [Maven Central](https://search.maven.org/) after publication.

```xml
<dependency>
    <groupId>org.moodminds.reactive</groupId>
    <artifactId>reactive-streams</artifactId>
    <version>${version}</version>
</dependency>
```

## Building from Source

You may need to build from source to use **Reactive Streams** (until it is in Maven Central) with Maven and JDK 1.8 at least.

## License
This project is going to be released under version 2.0 of the [Apache License][l].

[l]: https://www.apache.org/licenses/LICENSE-2.0