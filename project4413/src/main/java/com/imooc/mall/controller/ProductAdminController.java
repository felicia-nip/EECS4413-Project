package com.imooc.mall.controller;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.common.Constant;
import com.imooc.mall.common.ValidList;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.pojo.Product;
import com.imooc.mall.model.request.AddProductReq;
import com.imooc.mall.model.request.UpdateProductReq;
import com.imooc.mall.service.ProductService;
import com.imooc.mall.service.UploadService;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *  Back-End Product Management Controller
 */
@RestController
@Validated
public class ProductAdminController {

    @Autowired
    ProductService productService;

    @Autowired
    private UploadService uploadService;

    @PostMapping("/admin/product/add")
    public ApiRestResponse addProduct(@Valid @RequestBody AddProductReq addProductReq) {
        productService.add(addProductReq);
        return ApiRestResponse.success();
    }

    @PostMapping("/admin/upload/file")
    public ApiRestResponse upload(HttpServletRequest httpServletRequest,
            @RequestParam("file") MultipartFile file) {
        String result = uploadService.uploadFile(file);
        return ApiRestResponse.success(result);
    }


    @ApiOperation("Back-end update products")
    @PostMapping("/admin/product/update")
    public ApiRestResponse updateProduct(@Valid @RequestBody UpdateProductReq updateProductReq) {
        Product product = new Product();
        BeanUtils.copyProperties(updateProductReq, product);
        productService.update(product);
        return ApiRestResponse.success();
    }

    @ApiOperation("Delete products in the Back-End")
    @PostMapping("/admin/product/delete")
    public ApiRestResponse deleteProduct(@RequestParam Integer id) {
        productService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("batch ppdate sell status")
    @PostMapping("/admin/product/batchUpdateSellStatus")
    public ApiRestResponse batchUpdateSellStatus(@RequestParam Integer[] ids,
            @RequestParam Integer sellStatus) {
        productService.batchUpdateSellStatus(ids, sellStatus);
        return ApiRestResponse.success();
    }

    @ApiOperation("Back-end product list interface")
    @GetMapping("/admin/product/list")
    public ApiRestResponse list(@RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        PageInfo pageInfo = productService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("Back-end batch creating of products")
    @PostMapping("/admin/upload/product")
    public ApiRestResponse uploadProduct(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String newFileName = uploadService.getNewFileName(multipartFile);
        //Create file
        File fileDirectory = new File(Constant.FILE_UPLOAD_DIR);
        File destFile = new File(Constant.FILE_UPLOAD_DIR + newFileName);
        uploadService.createFile(multipartFile, fileDirectory, destFile);
        productService.addProductByExcel(destFile);
        return ApiRestResponse.success();
    }



    @PostMapping("/admin/upload/image")
    public ApiRestResponse uploadImage(HttpServletRequest httpServletRequest,
            @RequestParam("file") MultipartFile file) throws IOException {
        String result = uploadService.uploadImage(file);
        return ApiRestResponse.success(result);
    }


    @ApiOperation("Back-end batch update products")
    @PostMapping("/admin/product/batchUpdate")
    public ApiRestResponse batchUpdateProduct(@Valid @RequestBody List<UpdateProductReq> updateProductReqList) {
        for (int i = 0; i < updateProductReqList.size(); i++) {
            UpdateProductReq updateProductReq = updateProductReqList.get(i);
            //Approach, manual verification
            if (updateProductReq.getPrice() < 1) {
                throw new ImoocMallException(ImoocMallExceptionEnum.PRICE_TOO_LOW);
            }
            if (updateProductReq.getStock() > 10000) {
                throw new ImoocMallException(ImoocMallExceptionEnum.STOCK_TOO_MANY);
            }
            Product product = new Product();
            BeanUtils.copyProperties(updateProductReq, product);
            productService.update(product);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("Batch update products in the Back-End，")
    @PostMapping("/admin/product/batchUpdate2")
    public ApiRestResponse batchUpdateProduct2(@Valid @RequestBody ValidList<UpdateProductReq> updateProductReqList) {
        for (int i = 0; i < updateProductReqList.size(); i++) {
            UpdateProductReq updateProductReq = updateProductReqList.get(i);
            Product product = new Product();
            BeanUtils.copyProperties(updateProductReq, product);
            productService.update(product);
        }
        return ApiRestResponse.success();
    }


    @ApiOperation("Back-end batch update products，ValidList Verification")
    @PostMapping("/admin/product/batchUpdate3")
    public ApiRestResponse batchUpdateProduct3(@Valid @RequestBody List<UpdateProductReq> updateProductReqList) {
        for (int i = 0; i < updateProductReqList.size(); i++) {
            UpdateProductReq updateProductReq = updateProductReqList.get(i);
            Product product = new Product();
            BeanUtils.copyProperties(updateProductReq, product);
            productService.update(product);
        }
        return ApiRestResponse.success();
    }
}
