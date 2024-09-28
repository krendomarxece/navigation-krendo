package ph.edu.auf.navigationdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentFaveQuotesBinding

class FaveQuotesFragment : Fragment() {

    private var _binding: FragmentFaveQuotesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFaveQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val savedQuote = sharedPref.getString("SAVED_QUOTE", "")

        if (!savedQuote.isNullOrEmpty()) {
            binding.txtFaveQuote.text = savedQuote
        }

        // Remove the saved quote
        binding.btnRemoveFave.setOnClickListener {
            sharedPref.edit().remove("SAVED_QUOTE").apply()
            binding.txtFaveQuote.text = ""
            Toast.makeText(requireContext(), "Bookmark removed!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
