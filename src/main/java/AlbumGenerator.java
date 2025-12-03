import java.util.*;
import java.io.*;
import javax.swing.*;
public class AlbumGenerator
{
    static List <String> Playlists;

    static List <String> Albums=new LinkedList<>();

    static Random Generator=new Random();

    public static void GeneratePlaylist()
    {
        if(Albums.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Launching RandomPlaylistGenerator");

            boolean singleplaylistcreated=false;

            File directory=new File(System.getProperty("user.dir"));

            Playlists=getPlaylistFiles(directory.list());

            if(Playlists.isEmpty())
            {
                JOptionPane.showMessageDialog
                        (null, "You may have to add some playlist files to the directory first.");

                System.exit(0);
            }

            int playlist=0;

            String [] modi={"By Choice", "Randomly", "Everything", "Exit"};

            int modus = JOptionPane.showOptionDialog(
                    null,
                    "CHOOSE YOUR MODUS",
                    "Modus selection",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    modi,
                    null
                    );

            switch (modus) {
                case 0 : {
                    playlist = JOptionPane.showOptionDialog(
                            null,
                            "These are your playlists:",
                            "Playlist selection",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            formatPlaylists(Playlists),
                            null
                    );

                    singleplaylistcreated = true;

                } break;
                case 1 : {
                    playlist = Generator.nextInt(Playlists.size());

                    singleplaylistcreated = true;
                } break;
                case 2 : {
                    for (int i = 0; i < Playlists.size(); i++) {
                        Fill(i);
                    }
                } break;
                case 3: {
                    System.exit(0);
                }
            }

            if (singleplaylistcreated)
            {
                Fill(playlist);
            }

            GeneratePlaylist();
        }
        else Shuffle();
    }

    private static List<String> getPlaylistFiles(String[] files)
    {
        List<String> playlistFiles = new LinkedList<>();

        for (String file : files) {
            if (file.contains("txt")) {
                playlistFiles.add(file);
            }
        }

        return playlistFiles;
    }

    public static Object[] formatPlaylists(List<String> playlists) {
        List<String> formattedPlaylists=new LinkedList<>();

        for(String file: playlists){
            formattedPlaylists.add(file.split("\\.")[0]);
        }

        return formattedPlaylists.toArray();
    }

    private static void Fill(int playlist)
    {
        try {
            FillExc(Playlists.get(playlist));
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "File error. Trying again.");
        }
    }

    private static void FillExc (String playlist) throws IOException
    {
        FileReader fr = new FileReader(playlist);

        BufferedReader br = new BufferedReader(fr);

        String album;

        while( (album = br.readLine()) != null )
        {
            Albums.add(album);
        }

        br.close();
    }

    public static void Shuffle()
    {
        JOptionPane.showMessageDialog
                (null, "Attention, this playlist of "+Albums.size()+" albums rocks your face off!");

        int queue=Albums.size()-1;

        while (queue>=0)
        {
            String print;

            int point=Generator.nextInt(Albums.size());

            String album=Albums.get(point);

            if(queue>0)
            {
                print="Coming Up: "+album+"!! Queue: "+queue;
            }
            else
            {
                print="And finally: "+album+"!!!!!";
            }

            JOptionPane.showMessageDialog(null, print);

            Albums.remove(album);

            queue--;
        }
    }

    static void main() {
        GeneratePlaylist();

        System.exit(0);
    }
}