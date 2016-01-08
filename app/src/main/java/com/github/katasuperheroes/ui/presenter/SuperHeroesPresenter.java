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

package com.github.katasuperheroes.ui.presenter;

import com.github.katasuperheroes.model.SuperHero;
import com.github.katasuperheroes.usecase.GetSuperHeroes;
import java.util.List;

public class SuperHeroesPresenter extends Presenter<SuperHeroesPresenter.View> {

  private final GetSuperHeroes getSuperHeroes;

  public SuperHeroesPresenter(GetSuperHeroes getSuperHeroes) {
    this.getSuperHeroes = getSuperHeroes;
  }

  @Override public void initialize() {
    super.initialize();
    getSuperHeroes.getAll(new GetSuperHeroes.Callback() {
      @Override public void onSuperHeroesLoaded(List<SuperHero> superHeroes) {
        getView().showSuperHeroes(superHeroes);
      }
    });
  }

  public interface View extends Presenter.View {

    void showSuperHeroes(List<SuperHero> superHeroes);
  }
}
