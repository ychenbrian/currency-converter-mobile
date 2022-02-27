package com.deerangle.dacc.android.core.customui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.deerangle.dacc.android.R
import com.deerangle.dacc.android.ViewTextInputBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TextInputView(
    private val viewTag: String?,
    private val text: String?,
    private val hint: String?,
    private val delegate: Delegate
) : BottomSheetDialogFragment() {

    interface Delegate {
        fun textInputViewSubmit(viewTag: String?, text: String?)
        fun getInputType(): Int
    }

    private var bottomSheetBehavior: BottomSheetBehavior<View>? = null
    private lateinit var binding: ViewTextInputBinding

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = DataBindingUtil.inflate<ViewTextInputBinding>(
            layoutInflater,
            R.layout.view_text_input,
            null,
            false
        ).also {
            it.lifecycleOwner = viewLifecycleOwner
            binding = it
        }.root

        binding.apply {
            viewTextInputEtMain.apply {
                inputType = delegate.getInputType()
                text = SpannableStringBuilder(this@TextInputView.text)
                hint = this@TextInputView.hint
                requestFocus()
                this@TextInputView.text?.let { setSelection(it.length) }
            }

            viewTextInputBtSubmit.setOnClickListener {
                delegate.textInputViewSubmit(viewTag, viewTextInputEtMain.text.toString())
                dismiss()
            }
        }
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)

        val dialog: BottomSheetDialog =
            super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        view?.let {
            dialog.setContentView(it)
            bottomSheetBehavior = BottomSheetBehavior.from(it)
        }

        bottomSheetBehavior?.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO

        return dialog
    }

    override fun onStart() {
        super.onStart()

        // Setup the init state
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)

        binding.apply {
            delegate.textInputViewSubmit(viewTag, viewTextInputEtMain.text.toString())
        }
    }
}
