package com.summer.note.util;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * CreateBy:lijheng at 2019/10/30
 * describe:用于{@link androidx.viewpager.widget.ViewPager}的通用适配器，
 * 通过设置 {@link FragmentCreator} 防止内存泄露
 */
@SuppressWarnings("unused")
public class FragmentAdapter extends FragmentPagerAdapter {
    /**
     * Fragment构造器列表
     */
    private List<FragmentCreator> mCreatorList;

    /**
     * TabLayout的标题列表
     */
    private List<CharSequence> mTitleList;

    private FragmentSelectedListener mSelectedListener;

    /**
     * 当前选中的fragment
     */
    private Fragment mSelectedFragment;

    public FragmentAdapter(@NonNull FragmentManager fm, @NonNull List<FragmentCreator> creatorList) {
        this(fm, creatorList, null);
    }

    public FragmentAdapter(@NonNull FragmentManager fm, @NonNull List<FragmentCreator> creatorList, @Nullable List<CharSequence> titleList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mCreatorList = creatorList;
        this.mTitleList = titleList;
    }

    public FragmentAdapter(@NonNull FragmentActivity activity, @NonNull List<FragmentCreator> creatorList) {
        this(activity.getSupportFragmentManager(), creatorList);
    }

    public FragmentAdapter(@NonNull FragmentActivity activity, @NonNull List<FragmentCreator> creatorList, @NonNull List<CharSequence> titleList) {
        this(activity.getSupportFragmentManager(), creatorList, titleList);
    }

    public FragmentAdapter(@NonNull Fragment fragment, @NonNull List<FragmentCreator> creatorList) {
        this(fragment.getChildFragmentManager(), creatorList);
    }

    public FragmentAdapter(@NonNull Fragment fragment, @NonNull List<FragmentCreator> creatorList, @NonNull List<CharSequence> titleList) {
        this(fragment.getChildFragmentManager(), creatorList, titleList);
    }


    /**
     * 增加一项，没有标题的
     *
     * @param creator Fragment构造器
     */
    public void add(@NonNull FragmentCreator creator) {
        mCreatorList.add(creator);
    }

    /**
     * 增加一项，带标题的
     *
     * @param creator Fragment构造器
     * @param title   TabLayout标题
     */
    public void add(@NonNull FragmentCreator creator, CharSequence title) {
        add(creator);
        if (mTitleList == null) {
            throw new IllegalArgumentException("在初始化时未传入title");
        }
        mTitleList.add(title);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position 位置序号
     */
    @Override
    @NonNull
    public Fragment getItem(int position) {
        return mCreatorList.get(position).create();
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mCreatorList.size();
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        if (object instanceof Fragment) {
            mSelectedFragment = (Fragment) object;
        } else {
            mSelectedFragment = null;
        }
        if (mSelectedListener != null) {
            mSelectedListener.selected(mSelectedFragment);
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitleList != null) {
            return mTitleList.get(position);
        }
        return super.getPageTitle(position);
    }

    public void setFragmentSelectedListener(@NonNull FragmentSelectedListener mSelectedListener) {
        this.mSelectedListener = mSelectedListener;
    }

    /**
     * 获取当前选中的fragment实例
     *
     * @return 当前选中的fragment实例
     */
    @Nullable
    public Fragment getSelectedFragment() {
        return mSelectedFragment;
    }

    /**
     * fragment创建器
     */
    public interface FragmentCreator {

        /**
         * 创建fragment
         */
        Fragment create();
    }

    /**
     * fragment选择监听
     */
    public interface FragmentSelectedListener {

        void selected(@Nullable Fragment fragment);
    }
}
