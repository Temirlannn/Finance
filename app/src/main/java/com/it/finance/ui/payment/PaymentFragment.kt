package com.it.finance.ui.payment

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.it.finance.databinding.FragmentPaymentBinding
import com.it.finance.enums.CategoryType
import com.it.finance.enums.PaymentType
import com.it.finance.model.Payment
import com.it.finance.ui.base.BaseFragment
import com.it.finance.util.Manager.username

class PaymentFragment : BaseFragment<FragmentPaymentBinding>(), IPaymentView {

    private val presenter: PaymentPresenter by lazy {
        PaymentPresenter(this)
    }

    override fun setupView() = with(binding) {
        submit.setOnClickListener {
            val payment = Payment(
                id = 0,
                address = addressVal.text.toString(),
                amount = amountVal.text.toString().toInt(),
                description = descVal.text.toString(),
                category = CategoryType.findByCode(catSpinner.selectedItem.toString()),
                paymentType = PaymentType.findByType(spinner.selectedItem.toString()),
                usernameOfUse = username
            )
            presenter.savePayment(payment)
        }
    }

    override fun closeFragment() {
        findNavController().popBackStack()
    }

    override fun showErrorMessage(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}