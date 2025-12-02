import java.util.*;
import java.io.*;
import javax.swing.*;
public class AlbumGenerator
{
    static List <String> Albums=new LinkedList<>();

    static Random generator=new Random();

    static List <String> Playlists;

    public static void PlaylistGenerator()
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

                System.exit(-1);
            }

            int playlist=0;

            int playlistssize= Playlists.size(); // to be replaced later

            String modus=JOptionPane.showInputDialog
                    (null, "By Choice? => C; Randomly? => R; Everything? => E");

            if(modus == null)
            {
                System.exit(-1);
            }

            switch (modus) {
                case "C", "c" : {
                    String playlist1 = JOptionPane.showInputDialog
                            (null, "Which playlist do you choose? (Between 1 and " + playlistssize + ")");
                    try {
                        playlist = Integer.parseInt(playlist1);

                        if (playlist > playlistssize || playlist < 1) {
                            IncorrectNoAlert();
                        } else {
                            singleplaylistcreated = true;
                        }
                    } catch (NumberFormatException e) {
                        IncorrectNoAlert();
                    }
                } break;
                case "R", "r" : {
                    playlist = generator.nextInt(Playlists.size() + 1);

                    singleplaylistcreated = true;
                } break;
                case "E", "e" : {
                    for (int i = 1; i <= Playlists.size(); i++) {
                        Fill(i);
                    }
                } break;
                default : {
                    JOptionPane.showMessageDialog(null, "Please choose one of the three options.");
                    JOptionPane.showMessageDialog(null, "Trying Again.");
                }
            }

            if (singleplaylistcreated)
            {
                Fill(playlist);
            }

            if(!Albums.isEmpty())
            {
                JOptionPane.showMessageDialog
                        (null, "Attention, this playlist of "+Albums.size()+" albums rocks your face off!");
            }

            PlaylistGenerator();
        }
        else Shuffle();
    }

    private static void Fill(int playlist)
    {
        try {
            FillExc(Playlists.get(playlist - 1));
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

    private static List<String> getPlaylistFiles(String[] files)
    {
        List<String> f = new LinkedList<>();

        for (String file : files) {
            if (file.contains("txt")) {
                f.add(file);
            }
        }

        return f;
    }
    public static void IncorrectNoAlert()
    {
        JOptionPane.showMessageDialog(null, "You have to enter the number of one of your playlists.");
        JOptionPane.showMessageDialog(null, "Trying again.");
    }

    public static void Shuffle()
    {
        int queue=Albums.size()-1;

        while (queue>=0)
        {
            String print;

            int point=generator.nextInt(Albums.size());

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

    public static void main(String[] args) {
        PlaylistGenerator();
    }

}