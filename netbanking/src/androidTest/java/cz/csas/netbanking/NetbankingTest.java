package cz.csas.netbanking;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;

import cz.csas.cscore.CoreSDK;
import cz.csas.cscore.Environment;
import cz.csas.cscore.client.WebApiConfiguration;
import cz.csas.cscore.client.WebApiConfigurationImpl;
import cz.csas.cscore.judge.JudgeClient;

import static junit.framework.Assert.assertTrue;

/**
 * The type Netbanking test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
@RunWith(AndroidJUnit4.class)
public abstract class NetbankingTest {

    private final String TEST_BASE_URL_OAUTH = "http://csas-judge.herokuapp.com/widp/oauth2";
    private final String TEST_BASE_URL = "http://csas-judge.herokuapp.com/webapi";
    private final String JUDGE_BASE_URL = "http://csas-judge.herokuapp.com";
    private final String WEB_API_KEY_TEST = "TEST_API_KEY";

    /**
     * The M x judge session header.
     */
    protected String mXJudgeSessionHeader;
    /**
     * The M cs configuration.
     */
    protected WebApiConfiguration mWebApiConfiguration;
    /**
     * The M judge client.
     */
    protected JudgeClient mJudgeClient;
    /**
     * The M netbanking client.
     */
    protected NetbankingClient mNetbankingClient;


    /**
     * Set up.
     */
    @Before
    public void setUp() {
        //CoreSDK.getInstance().useLogger(new LogManagerImpl("TEST", LogLevel.DETAILED_DEBUG));
        mWebApiConfiguration = new WebApiConfigurationImpl(WEB_API_KEY_TEST, new Environment(TEST_BASE_URL, TEST_BASE_URL_OAUTH, false), "cs-CZ", null);
        mXJudgeSessionHeader = UUID.randomUUID().toString();
        mJudgeClient = new JudgeClient(JUDGE_BASE_URL, CoreSDK.getInstance().getLogger());
        mNetbankingClient = Netbanking.getInstance().init(mWebApiConfiguration).getNetbankingClient();
    }

    /**
     * Asserts the InputStream starts with a binary PDF file header.
     *
     * @param stream the stream
     * @throws IOException the io exception
     */
    public static void assertPdf(InputStream stream) throws IOException {
        byte[] expected = "%PDF".getBytes();
        byte[] topOfStream = new byte[4];
        stream.read(topOfStream, 0, 4);
        assertTrue(Arrays.equals(expected, topOfStream));
    }

    /**
     * Asserts the InputStream starts with a binary PNG file header.
     *
     * @param stream the stream
     * @throws IOException the io exception
     */
    public static void assertPng(InputStream stream) throws IOException {
        byte[] expected = {(byte) 0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a};
        byte[] topOfStream = new byte[expected.length];
        stream.read(topOfStream, 0, expected.length);
        assertTrue(Arrays.equals(expected, topOfStream));
    }
}
