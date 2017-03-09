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

package com.karumi.katasuperheroes.ui.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import butterknife.Bind;
import com.karumi.katasuperheroes.R;
import com.karumi.katasuperheroes.SuperHeroesApplication;
import com.karumi.katasuperheroes.model.SuperHero;
import com.karumi.marvelapiclient.CharacterApiClient;
import com.karumi.marvelapiclient.MarvelApiConfig;
import com.karumi.marvelapiclient.MarvelApiException;
import com.karumi.marvelapiclient.model.CharactersDto;
import com.karumi.marvelapiclient.model.CharactersQuery;
import com.karumi.marvelapiclient.model.MarvelResponse;
import java.util.List;

public class MainActivity extends BaseActivity {

  public static final String LOGTAG = "MainActivity";
  private SuperHeroesAdapter adapter;

  @Bind(R.id.tv_empty_case) View emptyCaseView;
  @Bind(R.id.recycler_view) RecyclerView recyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeDagger();
    initializeAdapter();
    initializeRecyclerView();
  }

  @Override protected void onResume() {
    super.onResume();
    new Thread(new Runnable() {
      @Override public void run() {
        MarvelApiConfig marvelApiConfig =
            new MarvelApiConfig.Builder("bf1f5d5f088f59478a3f68324fd1face",
                "d3fa0b1bad53d48b8bac7b9d4a02a860d24caca0").debug().build();
        CharacterApiClient characterApiClient = new CharacterApiClient(marvelApiConfig);
        CharactersQuery spider = CharactersQuery.Builder.create().withOffset(0).withLimit(10).build();
        try {
          MarvelResponse<CharactersDto> all = characterApiClient.getAll(spider);
          Log.d(LOGTAG, "Characters downloaded = " + all.getResponse().getCharacters().size());
        } catch (MarvelApiException e) {
          Log.e(LOGTAG, "Exception catch trying to download some characters from the Marvel API", e);
        }
      }
    }).start();
  }

  @Override public int getLayoutId() {
    return R.layout.main_activity;
  }

  public void showSuperHeroes(List<SuperHero> superHeroes) {
    adapter.addAll(superHeroes);
    adapter.notifyDataSetChanged();
  }

  public void openSuperHeroScreen(SuperHero superHero) {
    SuperHeroDetailActivity.open(this, superHero.getName());
  }

  public void showEmptyCase() {
    emptyCaseView.setVisibility(View.VISIBLE);
  }

  public void hideEmptyCase() {
    emptyCaseView.setVisibility(View.GONE);
  }

  private void initializeDagger() {
    SuperHeroesApplication app = (SuperHeroesApplication) getApplication();
    app.getMainComponent().inject(this);
  }

  private void initializeAdapter() {
    adapter = new SuperHeroesAdapter();
  }

  private void initializeRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(adapter);
  }
}
