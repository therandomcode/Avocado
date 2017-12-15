package com.example.wagner.avocado;

/**
 * Created by arkaroy on 12/3/17.
 * call back method which should be invoked whenever data
 * is received back from the database, at which point, the
 * page should continue doing whatever it is doing
 */

import java.util.ArrayList;

public interface DataReceived
{
    public void Success(String response);
}
