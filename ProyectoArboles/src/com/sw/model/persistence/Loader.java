package com.sw.model.persistence;

/**
 *
 * @author HikingCarrot7
 */
@FunctionalInterface
public interface Loader<L>
{

    public L load();
}
