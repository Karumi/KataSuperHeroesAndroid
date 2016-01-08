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
import com.squareup.picasso.Picasso;
import github.com.katasuperheroes.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class SuperHeroesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<SuperHero> superHeroes = new ArrayList<>();

  void addAll(Collection<SuperHero> collection) {
    superHeroes.addAll(collection);
  }

  void clear() {
    superHeroes.clear();
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.super_hero_row, parent, false);
    return new SuperHeroViewHolder(view);
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

    private final ImageView photoImageView;
    private final TextView nameTextView;

    public SuperHeroViewHolder(View itemView) {
      super(itemView);
      this.photoImageView = (ImageView) itemView.findViewById(R.id.iv_super_hero_photo);
      this.nameTextView = (TextView) itemView.findViewById(R.id.tv_super_hero_name);
    }

    public void render(SuperHero superHero) {
      renderSuperHeroPhoto(superHero.getPhoto());
      renderSuperHeroName(superHero.getName());
    }

    private void renderSuperHeroPhoto(String photo) {
      Picasso.with(getContext()).load(photo).fit().centerCrop().into(photoImageView);
    }

    private void renderSuperHeroName(String name) {
      nameTextView.setText(name);
    }

    private Context getContext() {
      return itemView.getContext();
    }
  }
}
