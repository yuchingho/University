using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MenuScript : MonoBehaviour {

    void Update()
    {
        if(Input.GetKey(KeyCode.Space)) { SceneManager.LoadScene("Game"); }
        if (Input.GetKey(KeyCode.Escape)) { Application.Quit(); }
    }
}