using UnityEngine;
using UnityEngine.SceneManagement;

public class MainMenu : MonoBehaviour
{
    public string LeveltoLoad;

    public void PlayGame()
    {
        SceneManager.LoadScene(LeveltoLoad);  //Loads next scene in build index
    }

    public void QuitGame()
    {
        Debug.Log("QUIT!");
        Application.Quit();  //Quits game (Debug shows quit message while inside Unity)
    }
}