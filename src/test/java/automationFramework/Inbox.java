package automationFramework;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FlagTerm;

import org.jsoup.Jsoup;

public class Inbox {

    private Folder inbox;
    private String host;
    private String user;
    private String password;

    public Inbox(String host, String user, String password) {
	this.host = host;
	this.user = user;
	this.password = password;
    }

    public String getMostRecentNewMessageContent() {

	Object content = null;

	try {

	    startSession();

	    this.inbox.open(Folder.READ_ONLY);
	    Flags seen = new Flags(Flags.Flag.SEEN);
	    FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
	    Message messages[] = inbox.search(unseenFlagTerm);
	    Message msg = messages[messages.length - 1];
	    content = msg.getContent();

	    return getMessageContent(content);

	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    /**
     * Gets the most recent unread message by a particular sender with a
     * particular subject title. NOTE: Will only look back 5 email's by default
     * 
     * @param sender
     *            The email address of the sender
     * @param subject
     *            The subject title of the email
     * @return The message content from the most recent message from the
     *         specified sender
     */
    public String getMostRecentUnreadMessageBy(String sender, String subject) {
	try {
	    startSession();

	    this.inbox.open(Folder.READ_ONLY);
	    Flags seen = new Flags(Flags.Flag.SEEN);
	    FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
	    Message messages[] = inbox.search(unseenFlagTerm);

	    for (int i = messages.length - 1; i > messages.length - 6; i--) {
		if (InternetAddress.toString(messages[i].getFrom()).equals(sender)) {
		    if (messages[i].getSubject().equals(subject))
			return getMessageContent(messages[i].getContent());
		}
	    }
	    return null;
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    private void startSession() {
	try {
	    Properties props = System.getProperties();
	    try {
		Session session = Session.getInstance(props, null);
		Store store = session.getStore("pop3");
		store.connect(host, user, password);
		this.inbox = store.getFolder("Inbox");
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return;
	}
    }

    private String getMessageContent(Object content) {

	if (content instanceof Multipart) {
	    Multipart mp = (Multipart) content;
	    try {
		BodyPart bp = mp.getBodyPart(0);
		return bp.getContent().toString();
	    } catch (Exception e) {
		e.printStackTrace();
		return null;
	    }
	} else {
	    return Jsoup.parse(content.toString()).text();
	}
    }
}
