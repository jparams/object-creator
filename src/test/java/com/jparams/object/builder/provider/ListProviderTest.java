package com.jparams.object.builder.provider;

import java.util.List;
import java.util.Set;

import com.jparams.object.builder.Context;
import com.jparams.object.builder.path.Path;
import com.jparams.object.builder.type.Type;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListProviderTest
{
    private ListProvider subject;

    @Before
    public void setUp()
    {
        subject = new ListProvider();
    }

    @Test
    public void testSupports()
    {
        assertThat(subject.supports(Type.forClass(List.class))).isTrue();
        assertThat(subject.supports(Type.forClass(Set.class))).isFalse();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testProvideReturnsEmptyOnUnknownGeneric()
    {
        final Context mockContext = mock(Context.class);
        when(mockContext.getPath()).thenReturn(new Path("", Type.forClass(List.class), null));
        when(mockContext.createChild(any(), any())).thenReturn("abc");

        final List<?> provided = subject.provide(mockContext);

        assertThat(provided).isEmpty();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testProvide()
    {
        final Context mockContext = mock(Context.class);
        final Type<?> type = Type.forClass(List.class).withGenerics(String.class);
        when(mockContext.getPath()).thenReturn(new Path("", type, null));
        when(mockContext.createChild(any(), any())).thenReturn("abc");

        final List<?> provided = subject.provide(mockContext);

        assertThat((List) provided).containsExactly("abc");

        verify(mockContext).createChild("[0]", type.getGenerics().get(0).getType());
    }
}