/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.katasuperheroes.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.katasuperheroes.model.SuperHero;
import com.github.katasuperheroes.ui.presenter.SuperHeroesPresenter;
import com.squareup.picasso.Picasso;
import github.com.katasuperheroes.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class SuperHeroesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final SuperHeroesPresenter presenter;
  private final List<SuperHero> superHeroes;

  public SuperHeroesAdapter(SuperHeroesPresenter presenter) {
    this.presenter = presenter;
    this.superHeroes = new ArrayList<>();
  }

  void addAll(Collection<SuperHero> collection) {
    superHeroes.addAll(collection);
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.super_hero_row, parent, false);
    return new SuperHeroViewHolder(view, presenter);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    SuperHeroViewHolder superHeroViewHolder = (SuperHeroViewHolder) holder;
    SuperHero superHero = superHeroes.get(position);
    superHeroViewHolder.render(superHero);
  }

  @Override public int getItemCount() {
    return superHeroes.size();
  }

  private static class SuperHeroViewHolder extends RecyclerView.ViewHolder {

    private final SuperHeroesPresenter presenter;
    private final ImageView photoImageView;
    private final TextView nameTextView;
    private final View avengersBadgeView;

    public SuperHeroViewHolder(View itemView, SuperHeroesPresenter presenter) {
      super(itemView);
      this.presenter = presenter;
      this.photoImageView = (ImageView) itemView.findViewById(R.id.iv_super_hero_photo);
      this.nameTextView = (TextView) itemView.findViewById(R.id.tv_super_hero_name);
      this.avengersBadgeView = itemView.findViewById(R.id.iv_avengers_badge);
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
}
