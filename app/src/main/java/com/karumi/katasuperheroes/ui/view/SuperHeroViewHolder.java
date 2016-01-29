package com.karumi.katasuperheroes.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.karumi.katasuperheroes.R;
import com.karumi.katasuperheroes.model.SuperHero;
import com.karumi.katasuperheroes.ui.presenter.SuperHeroesPresenter;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SuperHeroViewHolder extends RecyclerView.ViewHolder {

    private final SuperHeroesPresenter presenter;
    @Bind(R.id.iv_super_hero_photo)
    ImageView photoImageView;
    @Bind(R.id.tv_super_hero_name)
    TextView nameTextView;
    @Bind(R.id.iv_avengers_badge)
    View avengersBadgeView;

    public SuperHeroViewHolder(View itemView, SuperHeroesPresenter presenter) {
        super(itemView);
        this.presenter = presenter;
        ButterKnife.bind(this, itemView);
    }

    public void render(SuperHero superHero) {
        hookListeners(superHero);
        renderSuperHeroPhoto(superHero.getPhoto());
        renderSuperHeroName(superHero.getName());
        renderAvengersBadge(superHero.isAvenger());
    }

    private void hookListeners(final SuperHero superHero) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                presenter.onSuperHeroClicked(superHero);
            }
        });
    }

    private void renderSuperHeroPhoto(String photo) {
        Picasso.with(getContext()).load(photo).fit().centerCrop().into(photoImageView);
    }

    private void renderSuperHeroName(String name) {
        nameTextView.setText(name);
    }

    private void renderAvengersBadge(boolean isAvenger) {
        avengersBadgeView.setVisibility(isAvenger ? View.VISIBLE : View.GONE);
    }

    private Context getContext() {
        return itemView.getContext();
    }
}
