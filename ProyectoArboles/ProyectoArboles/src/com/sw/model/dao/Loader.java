package com.sw.model.dao;

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
