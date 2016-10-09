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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class StdOutErrUtil {

    private final ByteArrayOutputStream localStdOut = new ByteArrayOutputStream();
    private final ByteArrayOutputStream localStdErr = new ByteArrayOutputStream();

    private PrintStream currentStdOut;
    private PrintStream currentStdErr;

    void setUp() {
        localStdOut.reset();
        localStdErr.reset();
        currentStdOut = System.out;
        currentStdErr = System.err;
        System.setOut(new PrintStream(localStdOut));
        System.setErr(new PrintStream(localStdErr));
    }

    void tearDown() {
        System.setOut(currentStdOut);
        System.setErr(currentStdErr);
    }

    ByteArrayOutputStream getLocalStdOut() {
        return localStdOut;
    }

    ByteArrayOutputStream getLocalStdErr() {
        return localStdErr;
    }

}
