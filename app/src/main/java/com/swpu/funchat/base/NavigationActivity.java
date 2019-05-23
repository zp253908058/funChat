package com.swpu.funchat.base;

import android.os.Bundle;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
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
public abstract class NavigationActivity extends ToolbarActivity {

    protected NavController mNavController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_layout);

        mNavController = Navigation.findNavController(this, R.id.navigation_host_fragment);
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
}
