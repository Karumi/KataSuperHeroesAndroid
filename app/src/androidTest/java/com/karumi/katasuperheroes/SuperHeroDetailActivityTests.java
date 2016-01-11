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

package com.karumi.katasuperheroes;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import com.karumi.katasuperheroes.di.MainComponent;
import com.karumi.katasuperheroes.di.MainModule;
import com.karumi.katasuperheroes.model.SuperHero;
import com.karumi.katasuperheroes.model.SuperHeroesRepository;
import com.karumi.katasuperheroes.ui.view.SuperHeroDetailActivity;
import it.cosenonjaviste.daggermock.DaggerMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class) @LargeTest public class SuperHeroDetailActivityTests {

  @Rule public DaggerMockRule<MainComponent> daggerRule =
      new DaggerMockRule<>(MainComponent.class, new MainModule()).set(
          new DaggerMockRule.ComponentSetter<MainComponent>() {
            @Override public void setComponent(MainComponent component) {
              SuperHeroesApplication app =
                  (SuperHeroesApplication) InstrumentationRegistry.getInstrumentation()
                      .getTargetContext()
                      .getApplicationContext();
              app.setComponent(component);
            }
          });

  @Rule public ActivityTestRule<SuperHeroDetailActivity> activityRule =
      new IntentsTestRule<>(SuperHeroDetailActivity.class, true, false);

  @Mock SuperHeroesRepository repository;

  @Test public void hidesProgressBarOnSuperHeroLoaded() {
    SuperHero superHero = givenThereIsASuperHero();

    startActivity(superHero);

    onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())));
  }

  @Test public void showsSuperHeroName() {
    SuperHero superHero = givenThereIsASuperHero();

    startActivity(superHero);

    onView(allOf(withId(R.id.tv_super_hero_name), withText(superHero.getName()))).check(
        matches(isDisplayed()));
  }

  @Test public void showsSuperHeroDescription() {
    SuperHero superHero = givenThereIsASuperHero();

    startActivity(superHero);

    onView(withText(superHero.getDescription())).check(matches(isDisplayed()));
  }

  @Test public void doesNotShowAvengersBadgeIfSuperHeroIsNotPartOfTheAvengersTeam() {
    SuperHero superHero = givenThereIsASuperHero();

    startActivity(superHero);

    onView(withId(R.id.iv_avengers_badge)).check(matches(not(isDisplayed())));
  }

  @Test public void showsAvengersBadgeIfSuperHeroIsNotPartOfTheAvengersTeam() {
    SuperHero superHero = givenAnAvenger();

    startActivity(superHero);

    onView(withId(R.id.iv_avengers_badge)).check(matches(isDisplayed()));
  }

  private SuperHero givenThereIsASuperHero() {
    return givenThereIsASuperHero(false);
  }

  private SuperHero givenAnAvenger() {
    return givenThereIsASuperHero(true);
  }

  private SuperHero givenThereIsASuperHero(boolean isAvenger) {
    String superHeroName = "SuperHero";
    String superHeroPhoto = "https://i.annihil.us/u/prod/marvel/i/mg/c/60/55b6a28ef24fa.jpg";
    String superHeroDescription = "Super Hero Description";
    SuperHero superHero =
        new SuperHero(superHeroName, superHeroPhoto, isAvenger, superHeroDescription);
    when(repository.getByName(superHeroName)).thenReturn(superHero);
    return superHero;
  }

  private SuperHeroDetailActivity startActivity(SuperHero superHero) {
    Intent intent = new Intent();
    intent.putExtra("super_hero_name_key", superHero.getName());
    return activityRule.launchActivity(intent);
  }
}
