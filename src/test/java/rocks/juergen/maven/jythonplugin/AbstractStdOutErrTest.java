package rocks.juergen.maven.jythonplugin;

/*
 * Copyright 2016 Juergen Edelbluth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.junit.After;
import org.junit.Before;

abstract class AbstractStdOutErrTest {

    private final StdOutErrUtil util = new StdOutErrUtil();

    @Before
    public void setUp() {
        util.setUp();
    }

    @After
    public void tearDown() {
        util.tearDown();
    }

    StdOutErrUtil getStdOutErr() {
        return util;
    }

}
