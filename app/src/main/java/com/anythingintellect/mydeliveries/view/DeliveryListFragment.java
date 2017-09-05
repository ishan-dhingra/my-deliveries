package com.anythingintellect.mydeliveries.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anythingintellect.mydeliveries.MyDeliveriesApp;
import com.anythingintellect.mydeliveries.R;
import com.anythingintellect.mydeliveries.adapter.DeliveryAdapter;
import com.anythingintellect.mydeliveries.databinding.FragmentDeliveriesListBinding;
import com.anythingintellect.mydeliveries.di.ContextModule;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.util.Navigator;
import com.anythingintellect.mydeliveries.util.OnDeliverySelectedListener;
import com.anythingintellect.mydeliveries.viewmodel.DeliveryListViewModel;

import javax.inject.Inject;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryListFragment extends Fragment {

    @Inject
    DeliveryListViewModel viewModel;
    @Inject
    Navigator navigator;

    private DeliveryAdapter adapter;


    public DeliveryListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        adapter = new DeliveryAdapter(viewModel.getDeliveries(), navigator);
        viewModel.syncDeliveries();
    }

    private void inject() {
        ((MyDeliveriesApp)getActivity().getApplication())
                .getAppComponent()
                .plusContextModule(new ContextModule(getContext()))
                .inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.getDeliveries().addChangeListener(changeListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.getDeliveries().removeAllChangeListeners();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDeliveriesListBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_deliveries_list, container, false);
        binding.setVm(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRV((RecyclerView) view.findViewById(R.id.rvList));

    }

    private void setupRV(RecyclerView rvList) {
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
    }

    private final OrderedRealmCollectionChangeListener<RealmResults<Delivery>> changeListener =
            new OrderedRealmCollectionChangeListener<RealmResults<Delivery>>() {
                @Override
                public void onChange(RealmResults<Delivery> collection, OrderedCollectionChangeSet changeSet) {
                    // `null`  means the async query returns the first time.
                    if (changeSet == null) {
                        adapter.notifyDataSetChanged();
                        return;
                    }
                    // For deletions, the adapter has to be notified in reverse order.
                    OrderedCollectionChangeSet.Range[] deletions = changeSet.getDeletionRanges();
                    for (int i = deletions.length - 1; i >= 0; i--) {
                        OrderedCollectionChangeSet.Range range = deletions[i];
                        adapter.notifyItemRangeRemoved(range.startIndex, range.length);
                    }

                    OrderedCollectionChangeSet.Range[] insertions = changeSet.getInsertionRanges();
                    for (OrderedCollectionChangeSet.Range range : insertions) {
                        adapter.notifyItemRangeInserted(range.startIndex, range.length);
                    }

                    OrderedCollectionChangeSet.Range[] modifications = changeSet.getChangeRanges();
                    for (OrderedCollectionChangeSet.Range range : modifications) {
                        adapter.notifyItemRangeChanged(range.startIndex, range.length);
                    }

                }
            };
}
