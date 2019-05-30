package com.swpu.funchat.base;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.IdRes;
import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;

import com.swpu.funchat.R;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see NavigationActivity
 * @since 2019-05-23
 */
public abstract class NavigationActivity extends ToolbarActivity implements NavController.OnDestinationChangedListener {

    protected NavController mNavController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_layout);

        mNavController = Navigation.findNavController(this, R.id.navigation_host_fragment);
        mNavController.setGraph(getGraph());
        initNavigation(mNavController);
        mNavController.addOnDestinationChangedListener(this);
    }

    @NavigationRes
    protected abstract int getGraph();

    protected void initNavigation(NavController controller) {
        NavDestination current = controller.getCurrentDestination();
        if (current == null) {
            return;
        }
        CharSequence label = current.getLabel();
        mToolbar.setTitle(label);
    }

    public void setGraph(@NavigationRes int graphResId) {
        mNavController.setGraph(graphResId);
    }

    public void setGraph(@NavigationRes int graphResId, @Nullable Bundle startDestinationArgs) {
        mNavController.setGraph(graphResId, startDestinationArgs);
    }

    public void setGraph(@NonNull NavGraph graph) {
        mNavController.setGraph(graph);
    }

    public void setGraph(@NonNull NavGraph graph, @Nullable Bundle startDestinationArgs) {
        mNavController.setGraph(graph, startDestinationArgs);
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        CharSequence title = destination.getLabel();
        if (!TextUtils.isEmpty(title)) {
            mToolbar.setTitle(title);
        }
    }
}
