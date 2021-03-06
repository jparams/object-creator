package com.jparams.object.builder.path;

import java.util.Objects;

import com.jparams.object.builder.type.Type;

/**
 * Path on a class
 */
public class Path
{
    private final String name;
    private final Type<?> type;
    private final Path parent;

    public Path(final String name, final Type<?> type, final Path parent)
    {
        this.name = name;
        this.type = type;
        this.parent = parent;
    }

    public String getName()
    {
        return name;
    }

    public Type<?> getType()
    {
        return type;
    }

    public Path getParent()
    {
        return parent;
    }

    public int getDepth()
    {
        return parent == null ? 0 : parent.getDepth() + 1;
    }

    /**
     * Location from the base path
     *
     * @return location
     */
    public String getLocation()
    {
        return parent == null ? name : parent.getLocation() + "." + name;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        final Path path = (Path) o;
        return Objects.equals(name, path.name)
            && Objects.equals(type, path.type)
            && Objects.equals(parent, path.parent);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, type, parent);
    }

    @Override
    public String toString()
    {
        if (parent == null)
        {
            return name;
        }
        else
        {
            return parent.toString() + "." + name;
        }
    }
}
