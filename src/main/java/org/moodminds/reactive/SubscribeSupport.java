package org.moodminds.reactive;

import org.moodminds.elemental.Association;
import org.moodminds.elemental.KeyValue;
import org.reactivestreams.Subscription;

import java.util.NoSuchElementException;

/**
 * This interface acts as a potential (if supported) Producer, delivering items, exception
 * and completion signals to {@link Subscriber Subscribers}. If subscription is not supported due to the nature
 * of an implementation, it throws a {@link SubscribeSupportException}.
 * <p>
 * Each active {@link Subscriber} receives items via the {@link Subscriber#onNext(Object)} method in the same order,
 * with the possibility of encountering drops or errors. If an expected exception prevents the issuance of items to a
 * Subscriber, that Subscriber receives an {@link Subscriber#onError(E)} message. If unexpected error occurs,
 * the {@link Subscriber#onError(Throwable)} is triggered. No further items will be received.
 * <p>
 * When it's certain that no more items will be issued, a Subscriber receives an {@link Subscriber#onComplete()}} message.
 * <p>
 * Producers themselves must ensure that method invocations for each subscription are strictly ordered in accordance
 * with <a href="/java/util/concurrent/package-summary.html#MemoryVisibility"><i>happens-before</i></a> memory visibility.
 *
 * @param <V> the type of item values
 * @param <E> the type of potential exceptions
 */
public interface SubscribeSupport<V, E extends Exception> {

    /**
     * Subscribe to this item Producer with the given {@link org.reactivestreams.Subscriber} and {@link KeyValue}
     * varargs context. If already subscribed, or if the subscription attempt fails due to policy violations
     * or exceptions, the Subscriber's {@link org.reactivestreams.Subscriber#onError(Throwable)} method is called with
     * a {@link Throwable}. Otherwise, the Subscriber's {@link org.reactivestreams.Subscriber#onSubscribe(Subscription)}
     * method is invoked with a new {@link Subscription}.
     * <p>
     * Subscribers can enable receiving items by using the {@link Subscription#request(long)} method of the just
     * passed Subscription, or they can unsubscribe by using its {@link Subscription#cancel()} method.
     * <p>
     * The specified {@link KeyValue key-values} must not contain {@code null} keys or values to avoid {@link NullPointerException}.
     *
     * @param subscriber the given subscriber to receive items
     * @param ctx the given {@link KeyValue} varargs context
     * @throws NullPointerException if the given subscriber is {@code null} or {@link KeyValue key-values}
     * have {@code null} keys or values
     * @throws SubscribeSupportException if subscription is not supported
     */
    void subscribe(org.reactivestreams.Subscriber<? super V> subscriber, KeyValue<?, ?>... ctx);

    /**
     * Subscribe to this item Producer with the given {@link Subscriber}.
     * If already subscribed, or if the subscription attempt fails due to policy violations or exceptions, the Subscriber's
     * {@link Subscriber#onError(E)} or {@link Subscriber#onError(Throwable)} method is called with an {@link E}
     * or {@link Throwable}. Otherwise, the Subscriber's {@link Subscriber#onSubscribe(Subscription)} method is invoked
     * with a new {@link Subscription}.
     * <p>
     * Subscribers can enable receiving items by using the {@link Subscription#request(long)} method of the just
     * passed Subscription, or they can unsubscribe by using its {@link Subscription#cancel()} method.
     *
     * @param subscriber the given subscriber to receive items
     * @throws NullPointerException if the given subscriber is {@code null}
     * @throws SubscribeSupportException if subscription is not supported
     */
    default void subscribe(Subscriber<? super V, ? super E> subscriber) {
        subscribe(subscriber, new KeyValue<?, ?>[]{});
    }

    /**
     * Subscribe to this item Producer with the given {@link Subscriber} and {@link KeyValue} varargs context.
     * If already subscribed, or if the subscription attempt fails due to policy violations or exceptions, the Subscriber's
     * {@link Subscriber#onError(E)} or {@link Subscriber#onError(Throwable)} method is called with an {@link E}
     * or {@link Throwable}. Otherwise, the Subscriber's {@link Subscriber#onSubscribe(Subscription)} method is invoked
     * with a new {@link Subscription}.
     * <p>
     * Subscribers can enable receiving items by using the {@link Subscription#request(long)} method of the just
     * passed Subscription, or they can unsubscribe by using its {@link Subscription#cancel()} method.
     * <p>
     * The specified {@link KeyValue key-values} must not contain {@code null} keys or values to avoid {@link NullPointerException}.
     *
     * @param subscriber the given subscriber to receive items
     * @param ctx the given {@link KeyValue} varargs context
     * @throws NullPointerException if the given subscriber is {@code null} or {@link KeyValue key-values}
     * have {@code null} keys or values
     * @throws SubscribeSupportException if subscription is not supported
     */
    void subscribe(Subscriber<? super V, ? super E> subscriber, KeyValue<?, ?>... ctx);

    /**
     * Subscribe to this item Producer with the given {@link org.reactivestreams.Subscriber} and {@link Association}
     * context. If already subscribed, or if the subscription attempt fails due to policy violations or exceptions,
     * the Subscriber's {@link org.reactivestreams.Subscriber#onError(Throwable)} method is called with a {@link Throwable}.
     * Otherwise, the Subscriber's {@link org.reactivestreams.Subscriber#onSubscribe(Subscription)} method is invoked with
     * a new {@link Subscription}.
     * <p>
     * Subscribers can enable receiving items by using the {@link Subscription#request(long)} method of the just
     * passed Subscription, or they can unsubscribe by using its {@link Subscription#cancel()} method.
     * <p>
     * The specified {@link Association} must contain non-null keys and values, and its {@link Association#get(Object)}
     * method should raise a {@link NoSuchElementException} instead of returning {@code null} when no value is associated
     * with a key.
     *
     * @param subscriber the given subscriber to receive items
     * @param ctx the given {@link KeyValue} varargs context
     * @throws NullPointerException if the given subscriber is {@code null} or context is {@code null}
     * @throws SubscribeSupportException if subscription is not supported
     */
    void subscribe(org.reactivestreams.Subscriber<? super V> subscriber, Association<?, ?, ?> ctx);

    /**
     * Subscribe to this item Producer with the given {@link Subscriber} and {@link Association} context.
     * If already subscribed, or if the subscription attempt fails due to policy violations or exceptions, the Subscriber's
     * {@link Subscriber#onError(E)} or {@link Subscriber#onError(Throwable)} method is called with an {@link E}
     * or {@link Throwable}. Otherwise, the Subscriber's {@link Subscriber#onSubscribe(Subscription)} method is invoked
     * with a new {@link Subscription}.
     * <p>
     * Subscribers can enable receiving items by using the {@link Subscription#request(long)} method of the just
     * passed Subscription, or they can unsubscribe by using its {@link Subscription#cancel()} method.
     * <p>
     * The specified {@link Association} must contain non-null keys and values, and its {@link Association#get(Object)}
     * method should raise a {@link NoSuchElementException} instead of returning {@code null} when no value is associated
     * with a key.
     *
     * @param subscriber the given subscriber to receive items
     * @param ctx the given {@link KeyValue} varargs context
     * @throws NullPointerException if the given subscriber is {@code null} or context is {@code null}
     * @throws SubscribeSupportException if subscription is not supported
     */
    void subscribe(Subscriber<? super V, ? super E> subscriber, Association<?, ?, ?> ctx);

    /**
     * A message receiver from the {@link SubscribeSupport}. The methods in this
     * interface are called in a strict sequential order for each {@link Subscription}.
     *
     * @param <V> the type of items to consume
     * @param <E> the type of expected exceptions to consume
     */
    interface Subscriber<V, E extends Exception> extends org.reactivestreams.Subscriber<V> {

        /**
         * Receive expected fault terminal state. No further events will
         * be sent even if {@link Subscription#request(long)} is invoked again.
         *
         * @param error the exception signaled
         */
        void onError(E error);
    }
}
