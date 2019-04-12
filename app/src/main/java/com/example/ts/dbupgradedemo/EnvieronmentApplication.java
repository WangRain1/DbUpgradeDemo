/*
 * Copyright (c) 2019. Parrot Faurecia Automotive S.A.S. All rights reserved.
 */

package com.example.ts.dbupgradedemo;

import android.app.Application;

public class EnvieronmentApplication {

    static final EnvieronmentApplication ENVIERONMENT_APPLICATION = new EnvieronmentApplication();

    Application application;


    public static EnvieronmentApplication getInstance() {
        return ENVIERONMENT_APPLICATION;
    }
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
