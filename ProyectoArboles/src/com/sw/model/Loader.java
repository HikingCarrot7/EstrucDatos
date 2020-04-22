package com.sw.model;

/**
 *
 * @author HikingCarrot7
 */
@FunctionalInterface
public interface Loader<L>
{

    public L loadData();
}
