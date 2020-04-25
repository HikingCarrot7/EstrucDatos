package com.sw.model.persistence;

/**
 *
 * @author HikingCarrot7
 * @param <L>
 */
@FunctionalInterface
public interface Loader<L>
{

    public L load();
}
