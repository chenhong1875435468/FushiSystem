package com.chenhong;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * The interface Fushi test database loader.
 *
 * @author chenhong
 * @version 1.1.0
 */
public interface FushiTestDatabaseLoader {
    TestDatabase loadTestDatabase(String fileName) throws FileNotFoundException, IOException, DataFormatException, com.chenhong.DataFormatException;

}
