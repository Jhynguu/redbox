package fx.redbox.controller.donorCard;

import fx.redbox.controller.api.ApiResponse;
import fx.redbox.entity.donorCards.DonorCard;
import fx.redbox.entity.enums.DonorBloodKind;
import fx.redbox.entity.enums.Gender;
import fx.redbox.entity.users.User;
import fx.redbox.entity.users.UserAccount;
import fx.redbox.entity.users.UserInfo;
import fx.redbox.repository.donorCard.DonorCardRepository;
import fx.redbox.service.donorCard.DonorCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/donorcards")
@RestController
@RequiredArgsConstructor
public class DonorController {
    private final DonorCardService donorCardService;


    @PostMapping
    public ApiResponse saveDonorCard(@RequestBody DonorCard donorCardData) throws SQLException {
        donorCardService.saveDonorCard(donorCardData);

        return ApiResponse.success("헌혈증 정보 저장 성공", null);
    }

    @GetMapping("/{certificateNumber}")
    public ApiResponse showDonorCardByCertificateNumber(@PathVariable String certificateNumber) throws SQLException{
        Optional<DonorCard> findDonorCard = donorCardService.findDonorCard(certificateNumber);

        return ApiResponse.success("헌혈증 단일 정보 조회 성공", findDonorCard);
    }

    @GetMapping
    public ApiResponse showAllDonorCards() throws SQLException{
        List<DonorCard> donorCards = donorCardService.findAllDonorCards();

        return ApiResponse.success("헌혈증 전체 정보 조회 성공", donorCards);
    }

    @PatchMapping("/{certificateNumber}")
    public ApiResponse updateDonorCardByCertificateNumber(@PathVariable String certificateNumber, @RequestBody DonorCard updateDonorCard) throws SQLException{
        donorCardService.updateDonorCard(certificateNumber, updateDonorCard);
       
        return ApiResponse.success("헌혈증 정보 수정 성공", null);
    }
    
    @DeleteMapping("/{certificateNumber}")
    public ApiResponse deleteDonorCardByCertificateNumber(@PathVariable String certificateNumber) throws SQLException{
        donorCardService.deleteDonorCard(certificateNumber);
        
        return ApiResponse.success("헌혈증 정보 삭제 성공", null);
    }

}
