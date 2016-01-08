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
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.katasuperheroes.model.SuperHero;
import com.github.katasuperheroes.model.SuperHeroesRepository;
import com.github.katasuperheroes.ui.presenter.SuperHeroDetailPresenter;
import com.github.katasuperheroes.usecase.GetSuperHeroByName;
import com.squareup.picasso.Picasso;
import github.com.katasuperheroes.R;

public class SuperHeroDetailActivity extends BaseActivity implements SuperHeroDetailPresenter.View {

  private static final String SUPER_HERO_NAME_KEY = "super_hero_name_key";

  private ImageView superHeroPhotoImageView;
  private TextView superHeroNameTextView;
  private TextView superHeroDescriptionTextView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeSuperHeroView();
    initializePresenter();
  }

  @Override public int getLayoutId() {
    return R.layout.super_hero_detail_activity;
  }

  @Override public void showSuperHero(SuperHero superHero) {
    Picasso.with(this).load(superHero.getPhoto()).fit().centerCrop().into(superHeroPhotoImageView);
    superHeroNameTextView.setText(superHero.getName());
    superHeroDescriptionTextView.setText(superHero.getDescription());
  }

  public static void open(Context context, String superHeroName) {
    Intent intent = new Intent(context, SuperHeroDetailActivity.class);
    intent.putExtra(SUPER_HERO_NAME_KEY, superHeroName);
    context.startActivity(intent);
  }

  @Override protected void initializeToolbar() {
    super.initializeToolbar();
    String superHeroName = getSuperHeroName();
    setTitle(superHeroName);
  }

  private void initializeSuperHeroView() {
    superHeroPhotoImageView = (ImageView) findViewById(R.id.iv_super_hero_photo);
    superHeroNameTextView = (TextView) findViewById(R.id.tv_super_hero_name);
    superHeroDescriptionTextView = (TextView) findViewById(R.id.tv_super_hero_description);
  }

  private void initializePresenter() {
    GetSuperHeroByName getSuperHeroByName = new GetSuperHeroByName(new SuperHeroesRepository());
    SuperHeroDetailPresenter superHeroDetailPresenter =
        new SuperHeroDetailPresenter(getSuperHeroByName);
    superHeroDetailPresenter.setView(this);
    String name = getSuperHeroName();
    superHeroDetailPresenter.setName(name);
    superHeroDetailPresenter.initialize();
  }

  private String getSuperHeroName() {
    return getIntent().getExtras().getString(SUPER_HERO_NAME_KEY);
  }
}
