/*
 * Copyright (c) 2015, 2017, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.svm.core.posixsubst.headers;

import org.graalvm.nativeimage.Platforms;
import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CPointerTo;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.impl.DeprecatedPlatform;
import org.graalvm.word.PointerBase;

// Allow methods with non-standard names: Checkstyle: stop

/*
 * The definitions I need, manually translated from the C header file.
 */
@Platforms({DeprecatedPlatform.DARWIN_SUBSTITUTION.class, DeprecatedPlatform.LINUX_SUBSTITUTION.class})
@CContext(PosixSubstDirectives.class)
public class Ifaddrs {

    private Ifaddrs() {
    }

    @CStruct(addStructKeyword = true)
    public interface ifaddrs extends PointerBase {

        @CField
        ifaddrs ifa_next();

        @CField
        CCharPointer ifa_name();

        @CField
        int ifa_flags();

        @CField
        Socket.sockaddr ifa_addr();
    }

    @CPointerTo(nameOfCType = "struct ifaddrs*")
    public interface ifaddrsPointer extends PointerBase {
        Ifaddrs.ifaddrs read();
    }

    @CFunction
    public static native int getifaddrs(ifaddrsPointer ifap);

    @CFunction
    public static native void freeifaddrs(ifaddrs ifp);
}

// Checkstyle: resume
