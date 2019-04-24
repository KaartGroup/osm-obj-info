package org.openstreetmap.josm.plugins.osmobjinfo;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import javax.swing.JOptionPane;
import org.openstreetmap.josm.gui.Notification;
import org.openstreetmap.josm.plugins.osmobjinfo.OSMObjInfotDialog.AllOsmObjInfo;

import static org.openstreetmap.josm.tools.I18n.tr;
import org.openstreetmap.josm.tools.OpenBrowser;

/**
 *
 * @author ruben
 */
public class OSMObjInfoActions {
    public static final String COPY = "Copy: {0}";
    public static final String OPEN_IN_BROWSER = "Open in browser {0}";

    public static void copyUser(String user) {
        if (!user.isEmpty()) {
            String linkUser = "https://www.openstreetmap.org/user/" + user;
            StringSelection selection = new StringSelection(linkUser);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            new Notification(tr(COPY, linkUser)).setIcon(JOptionPane.INFORMATION_MESSAGE).setDuration(Notification.TIME_SHORT).show();
        }
    }

    public static void openinBrowserUser(String user) {
        if (!user.isEmpty()) {
            openInBrowser("https://www.openstreetmap.org/user/".concat(user));
        }

    }

    public static void openinBrowserUserNeis(String user) {
        if (!user.isEmpty()) {
            openInBrowser("https://hdyc.neis-one.org/?".concat(user));
        }
    }

    static void openinBrowserUserOsmComments(String user) {
        if (!user.isEmpty()) {
            openInBrowser( "https://www.mapbox.com/osm-comments/#/changesets/?q=users:".concat(user));
        }
    }

    public static void copyChangeset(String idChangeset) {
        if (!idChangeset.isEmpty()) {
            String linkchangeset = "https://www.openstreetmap.org/changeset/".concat(idChangeset);
            StringSelection selection = new StringSelection(linkchangeset);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            new Notification(tr(COPY, linkchangeset)).setIcon(JOptionPane.INFORMATION_MESSAGE).setDuration(Notification.TIME_SHORT).show();
        }
    }

    public static void openinBrowserChangeset(String idChangeset) {
        if (!idChangeset.isEmpty()) {
            openInBrowser("https://www.openstreetmap.org/changeset/".concat(idChangeset));
        }
    }

    public static void openinBrowserChangesetMap(String idChangeset) {
        if (!idChangeset.isEmpty()) {
            openInBrowser("https://osmcha.mapbox.com/".concat(idChangeset));
        }
    }

    public static void copyIdobj(List<AllOsmObjInfo> osmObjInfo) {
        if (osmObjInfo == null || osmObjInfo.isEmpty()) return;
        String linkobjid = "";
        StringBuilder builder = new StringBuilder();
        for (AllOsmObjInfo object : osmObjInfo) {
            builder.append("https://www.openstreetmap.org/")
                .append(object.typeObj).append("/").append(object.idObject);
            if (osmObjInfo.indexOf(object) < osmObjInfo.size() - 2) {
                builder.append(", ");
            }
            if (osmObjInfo.indexOf(object) == osmObjInfo.size() - 2) {
                if (osmObjInfo.size() == 2) {
                    builder.append(" ");
                } else {
                    builder.append(", ");
                }
                builder.append(tr("and"));
                builder.append(" ");
            }
        }
        linkobjid = builder.toString();
        StringSelection selection = new StringSelection(linkobjid);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        new Notification(tr(COPY, linkobjid)).setIcon(JOptionPane.INFORMATION_MESSAGE).setDuration(Notification.TIME_SHORT).show();
    }

    public static void openinBrowserIdobj(List<AllOsmObjInfo> osmObjInfo) {
        if (osmObjInfo.size() > 5 || osmObjInfo.isEmpty()) return;
        for (AllOsmObjInfo info : osmObjInfo) {
            if (info.typeObj != null && !info.idObject.isEmpty()) {
                openInBrowser("https://www.openstreetmap.org/" + typeObj + "/" + idobj);
            }
        }
    }

    public static void openinBrowserIdobjOsmDeepHistory(List<AllOsmObjInfo> osmObjInfo) {
        if (osmObjInfo.size() > 5 || osmObjInfo.isEmpty()) return;
        for (AllOsmObjInfo info : osmObjInfo) {
            if (info.typeObj != null && !info.idObject.isEmpty()) {
                openInBrowser("https://osmlab.github.io/osm-deep-history/#/" + typeObj + "/" + idobj);
            }
        }
    }

    public static void openinBrowserMapillary(String coords) {
        if (coords == null || coords.isEmpty()) return;
        String[] arrCoords = coords.split(",");
        openInBrowser("https://www.mapillary.com/app/?lat=" + arrCoords[0] + "&lng=" + arrCoords[1] + "&z=20&focus=map&dateFrom=2017-01-01");
    }

    public static void openinBrowserOpenstreetcam(String coords) {
        if (coords == null || coords.isEmpty()) return;
        String[] arrCoords = coords.split(",");
        openInBrowser("https://openstreetcam.org/map/@" + arrCoords[0] + "," + arrCoords[1] + ",18z");
    }

    public static void openInBrowserYandex(String coords) {
        if (coords == null || coords.isEmpty()) return;
        String[] arrCoords = coords.split(",");
        openInBrowser("https://yandex.com/maps/?l=stv,sta&ll=" + arrCoords[1] + "," + arrCoords[0] + "&z=18");
    }

    private static void openInBrowser(String url) {
        new Notification(tr("Open in browser {0}", url)).setIcon(JOptionPane.INFORMATION_MESSAGE).setDuration(Notification.TIME_SHORT).show();
        OpenBrowser.displayUrl(url);
    }
}
