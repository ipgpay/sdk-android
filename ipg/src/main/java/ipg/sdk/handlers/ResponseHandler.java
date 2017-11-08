package ipg.sdk.handlers;

/*
 * Copyright (c) 2017 IPG Group Limited
 * All rights reserved.
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE.txt file for details.
 */

//Call back handler
public interface ResponseHandler<T> {
    public void handle(T handleObject);
}
