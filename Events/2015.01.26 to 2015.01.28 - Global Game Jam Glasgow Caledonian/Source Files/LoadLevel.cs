using UnityEngine;
using System.Collections;

public class LoadLevel : MonoBehaviour {

    public void LoadNextLevel()
    {
        Application.LoadLevel("Falling");
    }
}
