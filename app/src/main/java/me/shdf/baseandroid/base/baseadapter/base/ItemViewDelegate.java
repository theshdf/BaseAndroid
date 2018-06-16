package me.shdf.baseandroid.base.baseadapter.base;


import me.shdf.baseandroid.base.baseadapter.ViewHolder;

/**
 * Created by zcm on 16/6/22.
 */
public interface ItemViewDelegate<T>
{

    public abstract int getItemViewLayoutId();

    public abstract boolean isForViewType(T item, int position);

    public abstract void convert(ViewHolder holder, T t, int position);



}
