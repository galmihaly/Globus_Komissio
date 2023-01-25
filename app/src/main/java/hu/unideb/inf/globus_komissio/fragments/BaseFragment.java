package hu.unideb.inf.globus_komissio.fragments;

import androidx.fragment.app.Fragment;

import hu.unideb.inf.globus_komissio.fragments.interfaces.IFragmentNavigationPresenter;
import hu.unideb.inf.globus_komissio.fragments.interfaces.IFragmentNavigationView;

public abstract class BaseFragment extends Fragment implements IFragmentNavigationView {

    protected IFragmentNavigationPresenter navigationPresenter;
    @Override
    public void atachPresenter(IFragmentNavigationPresenter presenter) {
        navigationPresenter = presenter;
    }
}
