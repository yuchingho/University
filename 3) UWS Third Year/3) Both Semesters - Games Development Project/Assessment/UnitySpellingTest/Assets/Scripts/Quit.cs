using UnityEngine;

public class Quit : MonoBehaviour
{
    public void quitgame()
    {
        Debug.Log("Quit");
        Application.Quit();
    }
}